package cz.cvut.fel.vyzkumodolnosti.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.vyzkumodolnosti.model.dto.UserIdReplyDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi.MethodDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi.ParticipantDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi.ResearchDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.Method;
import cz.cvut.fel.vyzkumodolnosti.repository.MethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MethodService {
    private final MethodRepository methodRepository;
    @Value("${vophp.api.url}")
    private String apiUrl;
    @Value("${vophp.api.accesstoken}")
    private String accessToken;

    @Autowired
    public MethodService(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    public List<Method> findMethodNamesByResearchNumber(String researchNumber) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            // Create HTTP request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl + "/api/persons?codes=" + researchNumber))
                    .GET()
                    .header("X-AUTH-TOKEN", accessToken)
                    .header("Content-Type", "application/json")
                    .build();
            // Send HTTP request
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ParticipantDto[] participantArr = om.readValue(response.body(), ParticipantDto[].class);
            List<ParticipantDto> participantDtos = new ArrayList<>(Arrays.asList(participantArr));
            if (participantDtos.isEmpty()) {
                return new ArrayList<>();
            }
            ParticipantDto participant = participantDtos.get(0); // list should have only one element
            List<String> methodNames = new ArrayList<>();
            for (ResearchDto researchDto : participant.getResearches()) {
                if (researchDto.getMethod() != null) {
                    methodNames.add(researchDto.getMethod().getTitle());
                }
            }
            return mapNamesToMethods(methodNames);
        } catch (InterruptedException | IOException e) {
            return new ArrayList<>(); // if there is an error return empty list
        }

    }

    public List<MethodDto> getAllMethods() {
        List<Method> methods = this.methodRepository.findAll();
        return methods.stream().map(m -> {
            MethodDto dto = new MethodDto();
            dto.setId(m.getId());
            dto.setTitle(m.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    private List<Method> mapNamesToMethods (List<String> names) {
        List<Method> methods = new ArrayList<>();
        for (var name : names) {
            methods.add(mapNameToMethod(name));
        }
        return methods;
    }

    private Method mapNameToMethod(String methodName) {
        Method m = methodRepository.findByName(methodName);
        if (m == null) {
            m = new Method();
            m.setName(methodName);
            methodRepository.save(m);
        }
        return m;
    }

}
