package id.my.hendisantika.kafkalongrunningtasks.web.controller;

import id.my.hendisantika.kafkalongrunningtasks.jms.service.KafkaConsumerService;
import id.my.hendisantika.kafkalongrunningtasks.web.model.TaskRequest;
import id.my.hendisantika.kafkalongrunningtasks.web.model.TaskResponse;
import id.my.hendisantika.kafkalongrunningtasks.web.model.TaskStatus;
import id.my.hendisantika.kafkalongrunningtasks.web.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-long-running-tasks
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/19/24
 * Time: 09:02
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TaskController {

    private final TaskService taskService;

    private final KafkaConsumerService kafkaConsumerService;

    @PostMapping
    @Operation(
            summary = "Add New Task Data",
            description = "Add New Task Data.",
            tags = {"Tutorial"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            TaskResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<TaskResponse> processAsync(@RequestBody TaskRequest taskRequest,
                                                     UriComponentsBuilder b) {
        String taskId = UUID.randomUUID().toString();
        UriComponents progressURL = b.path("/tasks/{id}/progress").buildAndExpand(taskId);
        taskService.process(taskId, taskRequest, b);
        return ResponseEntity.accepted().location(progressURL.toUri()).build();
    }

    @GetMapping("{taskId}/progress")
    @Operation(
            summary = "Check Task Progress Task Data",
            description = "Check Task Progress Task Data.",
            tags = {"Tutorial"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            TaskResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<?> processAsync(@PathVariable String taskId) {
        TaskStatus taskStatus = kafkaConsumerService.getLatestTaskStatus(taskId);
        if (taskStatus == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(taskStatus);
    }
}
