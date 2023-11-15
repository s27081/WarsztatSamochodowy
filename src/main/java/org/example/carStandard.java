package org.example;
public enum carStandard{
        A(1.1),
        B(1.2),
        C(1.5),
        D(1.6),
        E(2.0);

        carStandard(double standardValue) {
                this.standardValue = standardValue;
        }

        private final double standardValue;

        public double getStandardValue(){
                return this.standardValue;
        }
}

