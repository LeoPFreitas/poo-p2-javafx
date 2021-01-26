package org.example.domain.entities.person;

public class PessoaJuridica extends Person {
    private String cnpj;

    public PessoaJuridica() {
    }

    public PessoaJuridica(long id, String firstName, String lastName, String email, String cnpj) {
        super(id, firstName, lastName, email);
        this.cnpj = cnpj;
    }

    @Override
    public Double getImportDuties() {
        return 0.0D;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "cnpj='" + cnpj + '\'' +
                "} " + super.toString();
    }
}
