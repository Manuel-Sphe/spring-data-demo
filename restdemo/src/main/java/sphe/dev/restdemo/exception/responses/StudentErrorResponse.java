package sphe.dev.restdemo.exception.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentErrorResponse {
    private Integer status ;
    private String message;
    private Long timeStamp;

}
