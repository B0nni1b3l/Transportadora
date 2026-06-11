{

    private String logradouro;
    private String numero;
    private String cep;
    private String cidade;
    private UF uf;
    
    public Endereco(String logradouro, String numero, String cep, String cidade, UF uf) {

        setLogradouro(logradouro);
        setNumero(numero);
        setCep(cep);
        setCidade(cidade);
        setUf(uf);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {

        if (logradouro.isEmpty()) {
            System.out.println("Logradouro inválido.");
        } else {
            this.logradouro = logradouro;
        }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {

        if (numero.isEmpty()) {
            System.out.println("Número inválido.");
        } else {
            this.numero = numero;
        }
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {

        if (cep.isEmpty()) {
            System.out.println("CEP inválido.");
        } else {
            this.cep = cep;
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {

        if (cidade.isEmpty()) {
            System.out.println("Cidade inválida.");
        } else {
            this.cidade = cidade;
        }
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
    
    public void exibirDados() {

        System.out.println("Logradouro: " + logradouro);
        System.out.println("Número: " + numero);
        System.out.println("CEP: " + cep);
        System.out.println("Cidade: " + cidade);
        System.out.println("UF: " + uf);
    }
    
    @Override
    public String toString() {

        return logradouro + ", " + numero + " | CEP: " + cep + " | Cidade: " + cidade + " | UF: " + uf;
    }
}
