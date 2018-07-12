package team.brotherhoodofmutants.mutantengine.core.entity;

public enum NucleotideType {
        TYPES("ACGT");

        private final String fieldDescription;

        private NucleotideType(String value) {
                fieldDescription = value;
        }

        public String getTypes() {
                return fieldDescription;
        }
}
