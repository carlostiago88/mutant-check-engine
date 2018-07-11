package team.brotherhoodofmutants.mutantengine.usecase.enums;

public enum HumanType {
    IS_MUTANT("Do you want to join us, Brotherhood of Mutants?"), IS_NOT_MUTANT("Sorry, Magneto. You're wrong");

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
