package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PokemonTO {
    private Long codigo;
    @NotBlank
    @Size(max = 100)
    private String nome;
    @NotBlank
    @Size(max = 50)
    private String tipo;
    @NotNull
    @Positive
    private Integer nivel;
    @NotNull
    @Past
    private LocalDate dataDaCaptura;

    public PokemonTO() {
    }

    public PokemonTO(Long codigo, String nome, String tipo, Integer nivel, LocalDate dataDaCaptura) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.dataDaCaptura = dataDaCaptura;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public LocalDate getDataDaCaptura() {
        return dataDaCaptura;
    }

    public void setDataDaCaptura(LocalDate dataDaCaptura) {
        this.dataDaCaptura = dataDaCaptura;
    }
}
