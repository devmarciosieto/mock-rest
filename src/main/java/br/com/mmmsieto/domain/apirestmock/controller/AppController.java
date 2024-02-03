package br.com.mmmsieto.domain.apirestmock.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.InputStreamReader;


@RestController
@RequestMapping("/")
public class AppController {

    static int i = 0;

    @GetMapping("/**")
    public ResponseEntity<Resource> getFile(HttpServletRequest request) {
        System.out.println("GET" + i++);
        try {

            StringBuilder requestBody = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line).append('\n');
                }
            }

            String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            String[] segments = fullPath.split("/");
            String jsonFileName = segments[segments.length - 1];

            Path currentDir = Paths.get("").toAbsolutePath();
            Path filePath = currentDir.resolve(jsonFileName + ".json");

            Resource file = new FileSystemResource(filePath);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

         //   logRequest(request, file, requestBody, "get");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/**")
    public ResponseEntity<Resource> postFile(HttpServletRequest request) {
        try {

            StringBuilder requestBody = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line).append('\n');
                }
            }

            String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            String[] segments = fullPath.split("/");
            String jsonFileName = segments[segments.length - 1];

            Path currentDir = Paths.get("").toAbsolutePath();
            Path filePath = currentDir.resolve(jsonFileName + ".json");

            Resource file = new FileSystemResource(filePath);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            logRequest(request, file, requestBody, "post");

            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/**")
    public ResponseEntity<Resource> putFile(HttpServletRequest request) {
        try {

            StringBuilder requestBody = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line).append('\n');
                }
            }

            String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            String[] segments = fullPath.split("/");
            String jsonFileName = segments[segments.length - 1];

            Path currentDir = Paths.get("").toAbsolutePath();
            Path filePath = currentDir.resolve(jsonFileName + ".json");

            Resource file = new FileSystemResource(filePath);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            logRequest(request, file, requestBody, "put");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/**")
    public ResponseEntity<Resource> patchFile(HttpServletRequest request) {
        try {

            StringBuilder requestBody = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line).append('\n');
                }
            }

            String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            String[] segments = fullPath.split("/");
            String jsonFileName = segments[segments.length - 1];

            Path currentDir = Paths.get("").toAbsolutePath();
            Path filePath = currentDir.resolve(jsonFileName + ".json");

            Resource file = new FileSystemResource(filePath);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            logRequest(request, file, requestBody, "patch");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/**")
    public ResponseEntity<Resource> DeleteFile(HttpServletRequest request) {
        try {

            StringBuilder requestBody = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line).append('\n');
                }
            }

            String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            String[] segments = fullPath.split("/");
            String jsonFileName = segments[segments.length - 1];

            Path currentDir = Paths.get("").toAbsolutePath();
            Path filePath = currentDir.resolve(jsonFileName + ".json");

            Resource file = new FileSystemResource(filePath);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            logRequest(request, file, requestBody, "delete");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private void logRequest(HttpServletRequest request, Resource file, StringBuilder requestBody, String verbo) throws IOException {
        String fileName = verbo+"_log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".txt";
        Path logPath = Paths.get(fileName);

        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(logPath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            out.println("Timestamp: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            out.println("Request URI: " + request.getRequestURI());
            out.println("Query String: " + request.getQueryString());
            out.println("Request Method: " + request.getMethod());
            out.println("Response Content:");

            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }

            out.println("Request Body:");
            out.println(requestBody.toString());
        }
    }
}
