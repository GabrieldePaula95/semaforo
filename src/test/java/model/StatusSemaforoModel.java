package model;
import com.google.gson.annotations.Expose;
import lombok.Data;
@Data
public class StatusSemaforoModel {
    @Expose(serialize = false)
    private Long id;

    @Expose
    private String descricao;
}