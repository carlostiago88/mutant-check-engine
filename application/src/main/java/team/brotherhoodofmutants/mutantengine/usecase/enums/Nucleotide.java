package team.brotherhoodofmutants.mutantengine.usecase.enums;

public enum Nucleotide {
        TYPES("ACGT");

        private final String fieldDescription;

        private Nucleotide(String value) {
                fieldDescription = value;
        }

        public String getTypes() {
                return fieldDescription;
        }
}
