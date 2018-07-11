package team.brotherhoodofmutants.mutantengine.usecase.enums;

public enum HumanType {
    IS_MUTANT("Do you want to join us, Brotherhood of Mutants?"), IS_NOT_MUTANT("I'm sorry for the inconvenience. Bye weak human!");

    private String status;

    HumanType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
