package cz.cvut.fel.vyzkumodolnosti.services.xls;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.MctqEvaluationJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.MeqEvaluationJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.PsqiEvaluationJpaRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class ReportXlsExportService {


    @Autowired
    MctqEvaluationJpaRepository mctqRepository;
    @Autowired
    PsqiEvaluationJpaRepository psqiRepository;
    @Autowired
    MeqEvaluationJpaRepository meqRepository;

    public ByteArrayResource exportReportsToXlsForSelected(List<String> respIds) throws IOException {

        ArrayList<FormEvals> formEvals = new ArrayList<>();

        List<MctqEvaluation> exampleMctqs = new ArrayList<>();
        List<PsqiEvaluation> examplePsqis = new ArrayList<>();
        List<MeqEvaluation> exampleMeqs = new ArrayList<>();

        for(String respId : respIds) {
            FormEvals fe = new FormEvals(
                    respId,
                    mctqRepository.getAllByRespId(respId),
                    psqiRepository.getAllByRespId(respId),
                    meqRepository.getAllByRespId(respId)
            );

            if(exampleMctqs.size() < fe.mctqs.size()) {
                exampleMctqs = fe.mctqs;
            }
            if(examplePsqis.size() < fe.psqis.size()) {
                examplePsqis = fe.psqis;
            }
            if(exampleMeqs.size() < fe.meqs.size()) {
                exampleMeqs = fe.meqs;
            }

            formEvals.add(fe);
        }
        formEvals.sort(FormEvals::compareTo);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        XSSFRow headerRow = (XSSFRow) sheet.createRow(0);
        headerRow.createCell(0).setCellValue("resp id");

        int maxMctqsCells = formEvaluationHeadersToXls(Collections.singletonList(exampleMctqs), headerRow, 1) - 1;
        int maxPsqiCells = formEvaluationHeadersToXls(Collections.singletonList(examplePsqis), headerRow, maxMctqsCells) - maxMctqsCells - 1;
        int maxMeqCells = formEvaluationHeadersToXls(Collections.singletonList(exampleMeqs), headerRow, maxPsqiCells) - maxMctqsCells - maxPsqiCells - 1;

        for(int i = 0; i < formEvals.size(); i++) {
            FormEvals formEval = formEvals.get(i);
            XSSFRow dataRow = (XSSFRow) sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(formEval.respId);

            int gap;
            gap = formEvaluationDataToXls(Collections.singletonList(formEval.mctqs), dataRow, 1);
            for(int j = gap; j < maxMctqsCells + 1; j++)
                dataRow.createCell(j).setCellValue("NULL");

            gap = formEvaluationDataToXls(Collections.singletonList(formEval.psqis), dataRow, maxMctqsCells + 1);
            for(int j = gap; j < maxMctqsCells + maxPsqiCells + 1; j++)
                dataRow.createCell(j).setCellValue("NULL");

            gap = formEvaluationDataToXls(Collections.singletonList(formEval.meqs), dataRow, maxPsqiCells + maxMctqsCells + 1);
            for(int j = gap; j < maxMctqsCells + maxPsqiCells + maxMeqCells + 1; j++)
                dataRow.createCell(j).setCellValue("NULL");
        }

        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, maxMctqsCells + maxPsqiCells + maxMeqCells + 1));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        return new ByteArrayResource(outputStream.toByteArray());
    }

    public ByteArrayResource exportReportsToXls(String respId) throws IOException {
        List<MctqEvaluation> mctqs = mctqRepository.getAllByRespId(respId);
        List<PsqiEvaluation> psqis = psqiRepository.getAllByRespId(respId);
        List<MeqEvaluation> meqs = meqRepository.getAllByRespId(respId);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        XSSFRow headerRow = (XSSFRow) sheet.createRow(0);

        int index = 0;
        index = formEvaluationHeadersToXls(Collections.singletonList(mctqs), headerRow, index);
        index = formEvaluationHeadersToXls(Collections.singletonList(psqis), headerRow, index);
        formEvaluationHeadersToXls(Collections.singletonList(meqs), headerRow, index);

        XSSFRow dataRow = (XSSFRow) sheet.createRow(1);
        index = 0;
        index = formEvaluationDataToXls(Collections.singletonList(mctqs), dataRow, index);
        index = formEvaluationDataToXls(Collections.singletonList(psqis), dataRow, index);
        formEvaluationDataToXls(Collections.singletonList(meqs), dataRow, index);

        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, index));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        return new ByteArrayResource(outputStream.toByteArray());
    }

    private int formEvaluationHeadersToXls(List<Object> wrappedList, XSSFRow headerRow, int index) {

        List<Object> evalList = (List<Object>) wrappedList.get(0);

        if(!evalList.isEmpty()) {
            Class<?> entityClass = evalList.get(0).getClass();

            // Get the entity's properties
            Field[] fields = entityClass.getDeclaredFields();
            fields = Arrays.stream(fields)
                    .filter(field -> !field.getName().equals("submittedForm"))
                    .toArray(Field[]::new);

            // Create a header row with column names based on property names
            for(int j = 0; j < evalList.size(); j++) {
                for (Field field : fields) {
                    String propertyName = field.getName() + (j+1);
                    XSSFCell cell = headerRow.createCell(index++);
                    cell.setCellValue(propertyName);
                }
            }
        }
        return index;
    }

    private int formEvaluationDataToXls(List<Object> wrappedList, XSSFRow dataRow, int index) {

        List<Object> evalList = (List<Object>) wrappedList.get(0);

        Class<?> entityClass = evalList.get(0).getClass();

        Field[] fields = entityClass.getDeclaredFields();
        fields = Arrays.stream(fields)
                .filter(field -> !field.getName().equals("submittedForm"))
                .toArray(Field[]::new);

        for (Object entity : evalList) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object propertyValue;
                try {
                    propertyValue = field.get(entity);
                } catch (IllegalAccessException e) {
                    propertyValue = "";
                }
                XSSFCell cell = dataRow.createCell(index++);
                cell.setCellValue(propertyValue != null ? propertyValue.toString() : "ERROR");
            }
        }
        return index;
    }
}

class FormEvals implements Comparable<FormEvals> {

    String respId;
    List<MctqEvaluation> mctqs;
    List<PsqiEvaluation> psqis;
    List<MeqEvaluation> meqs;

    public FormEvals(String respId, List<MctqEvaluation> mctqs, List<PsqiEvaluation> psqis, List<MeqEvaluation> meqs) {
        this.respId = respId;
        this.mctqs = mctqs;
        this.psqis = psqis;
        this.meqs = meqs;
    }


    @Override
    public int compareTo(FormEvals formEvals) {
        return Integer.compare(
                this.mctqs.size() + this.meqs.size() + this.psqis.size(),
                formEvals.mctqs.size() + formEvals.meqs.size() + formEvals.psqis.size());
    }
}