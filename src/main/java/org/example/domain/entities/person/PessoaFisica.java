package org.example.domain.entities.person;

public class PessoaFisica extends Person {
    private String cpf;
    private String rg;

    public PessoaFisica() {
    }

    public PessoaFisica(long id, String firstName, String lastName, String email, String cpf, String rg) {
        super(id, firstName, lastName, email);
        this.cpf = cpf;
        this.rg = rg;
    }

    @Override
    public Double getImportDuties() {
        return 0.0D;
    }

    @Override
    public String getPersonType() {
        return "Física";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                "} " + super.toString();
    }
}
