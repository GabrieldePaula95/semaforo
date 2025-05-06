package model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UsuarioModel {

    @Expose(serialize = false)
    private Long id;
    @Expose
    private String nome;
    @Expose
    private String email;
    @Expose
    private String senha;
    @Expose
    private String papel; // Ex: ADMIN, OPERADOR, VISITANTE{
}
