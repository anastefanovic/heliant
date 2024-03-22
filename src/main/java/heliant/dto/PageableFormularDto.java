package heliant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PageableFormularDto {
    private List<FormularDto> formularList;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
