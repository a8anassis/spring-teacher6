package gr.aueb.cf.teacherapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherInsertDTO {

    @NotNull(message = "Firstname can not ne null.")
    @Size(min = 2, message = "Firstname must be at least 2 characters long.")
    private String firstname;

    @NotNull(message = "Lastname can not ne null.")
    @Size(min = 2, message = "Lastname must be at least 2 characters long.")
    private String lastname;

    @Pattern(regexp = "\\d{9,}", message = "Vat must be at least 9 digits long.")
    private String vat;

    @NotNull(message = "Region must not be null.")
    private Long regionId;
}
