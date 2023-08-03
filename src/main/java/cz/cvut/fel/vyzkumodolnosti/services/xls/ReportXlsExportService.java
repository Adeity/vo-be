package cz.cvut.fel.vyzkumodolnosti.services.xls;

import cz.cvut.fel.vyzkumodolnosti.model.dto.device.DeviceSleepEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.MctqEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.MeqEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.PsqiEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceSleepEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.MctqEvaluationJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.MeqEvaluationJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.PsqiEvaluationJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.device.DeviceSleepEvaluationRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.device.mappers.DeviceSleepEvaluationXlsMapper;
import cz.cvut.fel.vyzkumodolnosti.services.forms.xls.MctqEvaluationXlsMapper;
import cz.cvut.fel.vyzkumodolnosti.services.forms.xls.MeqEvaluationXlsMapper;
import cz.cvut.fel.vyzkumodolnosti.services.forms.xls.PsqiEvaluationXlsMapper;
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
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ReportXlsExportService {

    @Autowired
    MctqEvaluationJpaRepository mctqRepository;
    @Autowired
    PsqiEvaluationJpaRepository psqiRepository;
    @Autowired
    MeqEvaluationJpaRepository meqRepository;
    @Autowired
    DeviceSleepEvaluationRepository deviceSleepRepository;


    public ByteArrayResource exportReportsToXlsForAll() throws IOException {
        List<MctqEvaluation> mctqs = mctqRepository.findAllByOrderBySubmittedFormCreated()
                .stream().filter(eval -> eval.getSubmittedForm().getResearchParticipant() != null).collect(Collectors.toList());
        List<PsqiEvaluation> psqis = psqiRepository.findAllByOrderBySubmittedFormCreated()
                .stream().filter(eval -> eval.getSubmittedForm().getResearchParticipant() != null).collect(Collectors.toList());
        List<MeqEvaluation> meqs = meqRepository.findAllByOrderBySubmittedFormCreated()
                .stream().filter(eval -> eval.getSubmittedForm().getResearchParticipant() != null).collect(Collectors.toList());
        List<DeviceSleepEvaluation> sleeps = deviceSleepRepository.findAllOrderByCreated()
                .stream().filter(sleep -> sleep.getResearchParticipant() != null).collect(Collectors.toList());

        Map<String, List<MctqEvaluation>> mctqMap = new HashMap<>();
        for (MctqEvaluation mctq : mctqs) {
            if (!mctqMap.containsKey(mctq.getSubmittedForm().getResearchParticipant().getResearchNumber())) {
                mctqMap.put(mctq.getSubmittedForm().getResearchParticipant().getResearchNumber(), new ArrayList<>());
            }
            mctqMap.get(mctq.getSubmittedForm().getResearchParticipant().getResearchNumber()).add(mctq);
        }


        Map<String, List<PsqiEvaluation>> psqiMap = new HashMap<>();
        for (PsqiEvaluation psqi : psqis) {
            if (!psqiMap.containsKey(psqi.getSubmittedForm().getResearchParticipant().getResearchNumber())) {
                psqiMap.put(psqi.getSubmittedForm().getResearchParticipant().getResearchNumber(), new ArrayList<>());
            }
            psqiMap.get(psqi.getSubmittedForm().getResearchParticipant().getResearchNumber()).add(psqi);
        }

        Map<String, List<MeqEvaluation>> meqMap = new HashMap<>();
        for (MeqEvaluation meq : meqs) {
            if (!meqMap.containsKey(meq.getSubmittedForm().getResearchParticipant().getResearchNumber())) {
                meqMap.put(meq.getSubmittedForm().getResearchParticipant().getResearchNumber(), new ArrayList<>());
            }
            meqMap.get(meq.getSubmittedForm().getResearchParticipant().getResearchNumber()).add(meq);
        }

        Map<String, List<DeviceSleepEvaluation>> sleepMap = new HashMap<>();
        for (DeviceSleepEvaluation sleep : sleeps) {
            if (!sleepMap.containsKey(sleep.getResearchParticipant().getResearchNumber())) {
                sleepMap.put(sleep.getResearchParticipant().getResearchNumber(), new ArrayList<>());
            }
            sleepMap.get(sleep.getResearchParticipant().getResearchNumber()).add(sleep);
        }

        Set<String> respIds = new HashSet<>();
        respIds.addAll(mctqMap.keySet());
        respIds.addAll(psqiMap.keySet());
        respIds.addAll(meqMap.keySet());
        respIds.addAll(sleepMap.keySet());

        List<MctqEvaluationXlsDto> exampleMctqs = new ArrayList<>();
        List<PsqiEvaluationXlsDto> examplePsqis = new ArrayList<>();
        List<MeqEvaluationXlsDto> exampleMeqs = new ArrayList<>();
        List<DeviceSleepEvaluationXlsDto> exampleSleeps = new ArrayList<>();

        List<FormEvals> formEvals = new ArrayList<>();

        for(String respId : respIds) {
            FormEvals fe = new FormEvals(
                    respId,
                    mctqMap.containsKey(respId) ? mctqMap.get(respId) : new ArrayList<>(),
                    psqiMap.containsKey(respId) ? psqiMap.get(respId) : new ArrayList<>(),
                    meqMap.containsKey(respId) ? meqMap.get(respId) : new ArrayList<>(),
                    sleepMap.containsKey(respId) ? sleepMap.get(respId) : new ArrayList<>()
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
            if(exampleSleeps.size() < fe.sleeps.size()) {
                exampleSleeps = fe.sleeps;
            }

            formEvals.add(fe);
        }
        formEvals.sort(FormEvals::compareTo);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        XSSFRow headerRow = (XSSFRow) sheet.createRow(0);
        headerRow.createCell(0).setCellValue("resp id");

        int maxMctqsCells = formEvaluationHeadersToXls(Collections.singletonList(exampleMctqs), headerRow, 1) - 1;
        int maxPsqiCells = formEvaluationHeadersToXls(Collections.singletonList(examplePsqis), headerRow, maxMctqsCells + 1) - maxMctqsCells - 1;
        int maxMeqCells = formEvaluationHeadersToXls(Collections.singletonList(exampleMeqs), headerRow, maxMctqsCells + maxPsqiCells + 1) - maxMctqsCells - maxPsqiCells - 1;
        int maxSleepCells = formEvaluationHeadersToXls(Collections.singletonList(exampleSleeps), headerRow, maxMctqsCells + maxPsqiCells + maxMeqCells + 1) - maxMctqsCells - maxPsqiCells - maxMeqCells - 1;

        for(int i = 0; i < formEvals.size(); i++) {
            FormEvals formEval = formEvals.get(i);

            XSSFRow dataRow = (XSSFRow) sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(formEval.researchNumber);

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

            gap = formEvaluationDataToXls(Collections.singletonList(formEval.sleeps), dataRow, maxPsqiCells + maxMctqsCells + maxMeqCells + 1);
            for(int j = gap; j < maxMctqsCells + maxPsqiCells + maxMeqCells + maxSleepCells + 1; j++)
                dataRow.createCell(j).setCellValue("NULL");
        }

        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, maxMctqsCells + maxPsqiCells + maxMeqCells + maxSleepCells + 1));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        return new ByteArrayResource(outputStream.toByteArray());
    }

    public ByteArrayResource exportReportsToXlsForSelected(List<String> respIds) throws IOException {

        List<MctqEvaluation> mctqs = mctqRepository.getAllBySubmittedFormResearchParticipantResearchNumberIn(respIds);
        List<PsqiEvaluation> psqis = psqiRepository.getAllBySubmittedFormResearchParticipantResearchNumberIn(respIds);
        List<MeqEvaluation> meqs = meqRepository.getAllBySubmittedFormResearchParticipantResearchNumberIn(respIds);
        List<DeviceSleepEvaluation> sleeps = deviceSleepRepository.getAllByResearchParticipantResearchNumberInOrderByCreated(respIds);

        List<FormEvals> formEvals = new ArrayList<>();

        List<MctqEvaluationXlsDto> exampleMctqs = new ArrayList<>();
        List<PsqiEvaluationXlsDto> examplePsqis = new ArrayList<>();
        List<MeqEvaluationXlsDto> exampleMeqs = new ArrayList<>();
        List<DeviceSleepEvaluationXlsDto> exampleSleeps = new ArrayList<>();

        for(String respId : respIds) {
            FormEvals fe = new FormEvals(
                    respId,
                    mctqs.stream().filter(m -> Objects.equals(m.getSubmittedForm().getResearchParticipant().getResearchNumber(), respId)).collect(Collectors.toList()),
                    psqis.stream().filter(p -> Objects.equals(p.getSubmittedForm().getResearchParticipant().getResearchNumber(), respId)).collect(Collectors.toList()),
                    meqs.stream().filter(m -> Objects.equals(m.getSubmittedForm().getResearchParticipant().getResearchNumber(), respId)).collect(Collectors.toList()),
                    sleeps.stream().filter(s -> Objects.equals(s.getResearchParticipant().getResearchNumber(), respId)).collect(Collectors.toList())
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
            if(exampleSleeps.size() < fe.sleeps.size()) {
                exampleSleeps = fe.sleeps;
            }

            formEvals.add(fe);
        }
        formEvals.sort(FormEvals::compareTo);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        XSSFRow headerRow = (XSSFRow) sheet.createRow(0);
        headerRow.createCell(0).setCellValue("resp id");

        int maxMctqsCells = formEvaluationHeadersToXls(Collections.singletonList(exampleMctqs), headerRow, 1) - 1;
        int maxPsqiCells = formEvaluationHeadersToXls(Collections.singletonList(examplePsqis), headerRow, maxMctqsCells + 1) - maxMctqsCells - 1;
        int maxMeqCells = formEvaluationHeadersToXls(Collections.singletonList(exampleMeqs), headerRow, maxMctqsCells + maxPsqiCells + 1) - maxMctqsCells - maxPsqiCells - 1;
        int maxSleepCells = formEvaluationHeadersToXls(Collections.singletonList(exampleSleeps), headerRow, maxMctqsCells + maxPsqiCells + maxMeqCells + 1) - maxMctqsCells - maxPsqiCells - maxMeqCells - 1;

        for(int i = 0; i < formEvals.size(); i++) {
            FormEvals formEval = formEvals.get(i);

            XSSFRow dataRow = (XSSFRow) sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(formEval.researchNumber);

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

            gap = formEvaluationDataToXls(Collections.singletonList(formEval.sleeps), dataRow, maxPsqiCells + maxMctqsCells + maxMeqCells + 1);
            for(int j = gap; j < maxMctqsCells + maxPsqiCells + maxMeqCells + maxSleepCells + 1; j++)
                dataRow.createCell(j).setCellValue("NULL");
        }

        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, maxMctqsCells + maxPsqiCells + maxMeqCells + maxSleepCells + 1));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        return new ByteArrayResource(outputStream.toByteArray());
    }

    public ByteArrayResource exportReportsToXls(String respId) throws IOException {

        List<MctqEvaluation> mctqs = mctqRepository.getAllByRespId(respId);
        List<PsqiEvaluation> psqis = psqiRepository.getAllByRespId(respId);
        List<MeqEvaluation> meqs = meqRepository.getAllByRespId(respId);
        List<DeviceSleepEvaluation> sleeps = deviceSleepRepository.getAllByResearchParticipantResearchNumber(respId);

        FormEvals fe = new FormEvals(respId, mctqs, psqis, meqs, sleeps);

        if(fe.getSize() == 0) {
            return null;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        XSSFRow headerRow = (XSSFRow) sheet.createRow(0);

        int index = 0;
        index = formEvaluationHeadersToXls(Collections.singletonList(fe.mctqs), headerRow, index);
        index = formEvaluationHeadersToXls(Collections.singletonList(fe.psqis), headerRow, index);
        index = formEvaluationHeadersToXls(Collections.singletonList(fe.meqs), headerRow, index);

        if(!fe.sleeps.isEmpty())
            formEvaluationHeadersToXls(Collections.singletonList(fe.sleeps), headerRow, index);


        XSSFRow dataRow = (XSSFRow) sheet.createRow(1);
        index = 0;
        index = formEvaluationDataToXls(Collections.singletonList(fe.mctqs), dataRow, index);
        index = formEvaluationDataToXls(Collections.singletonList(fe.psqis), dataRow, index);
        index = formEvaluationDataToXls(Collections.singletonList(fe.meqs), dataRow, index);

        if(!fe.sleeps.isEmpty())
            formEvaluationDataToXls(Collections.singletonList(fe.sleeps), dataRow, index);

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
                    .filter(this::filterFields)
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

        if(evalList.isEmpty())
            return index;
        Class<?> entityClass = evalList.get(0).getClass();

        Field[] fields = entityClass.getDeclaredFields();
        fields = Arrays.stream(fields)
                .filter(this::filterFields)
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

    private boolean filterFields(Field field) {
        return !field.getName().equals("submittedForm")
                && !field.getName().equals("researchParticipant")
                && !field.getName().equals("version");
    }
}

class FormEvals implements Comparable<FormEvals> {

    MctqEvaluationXlsMapper mctqXlsMapper;
    PsqiEvaluationXlsMapper psqiXlsMapper;
    MeqEvaluationXlsMapper meqXlsMapper;
    DeviceSleepEvaluationXlsMapper deviceXlsMapper;
    String researchNumber;
    List<MctqEvaluationXlsDto> mctqs;
    List<PsqiEvaluationXlsDto> psqis;
    List<MeqEvaluationXlsDto> meqs;
    List<DeviceSleepEvaluationXlsDto> sleeps;

    public FormEvals(String researchNumber, List<MctqEvaluation> mctqs, List<PsqiEvaluation> psqis, List<MeqEvaluation> meqs, List<DeviceSleepEvaluation> sleeps) {

        this.mctqXlsMapper = new MctqEvaluationXlsMapper();
        this.psqiXlsMapper = new PsqiEvaluationXlsMapper();
        this.meqXlsMapper = new MeqEvaluationXlsMapper();
        this.deviceXlsMapper = new DeviceSleepEvaluationXlsMapper();

        this.researchNumber = researchNumber;
        this.setMctqs(mctqs);
        this.setPsqis(psqis);
        this.setMeqs(meqs);
        this.setSleeps(sleeps);
    }

    public void setMctqs(List<MctqEvaluation> mctqs) {
        this.mctqs = mctqs.stream()
                .map(me -> mctqXlsMapper.entityToXls(me))
                .collect(Collectors.toList());
    }

    public void setPsqis(List<PsqiEvaluation> psqis) {
        this.psqis = psqis.stream()
                .map(pe -> psqiXlsMapper.entityToXls(pe))
                .collect(Collectors.toList());
    }

    public void setMeqs(List<MeqEvaluation> meqs) {
        this.meqs = meqs.stream()
                .map(me -> meqXlsMapper.entityToXls(me))
                .collect(Collectors.toList());
    }

    public void setSleeps(List<DeviceSleepEvaluation> sleeps) {
        this.sleeps = sleeps.stream()
                .map(me -> deviceXlsMapper.entityToXls(me))
                .collect(Collectors.toList());
    }


    public int getSize() {
        return this.mctqs.size() + this.meqs.size() + this.psqis.size() + this.sleeps.size();
    }

    @Override
    public int compareTo(FormEvals formEvals) {
        return Integer.compare(formEvals.getSize(), this.getSize());
    }
}