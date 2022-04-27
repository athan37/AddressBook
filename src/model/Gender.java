package model;

public enum Gender {
	F {
		@Override
		public String toString() {
			return "Female";
		}
	}, 
	M {
		@Override
		public String toString() {
			return "Male";
		}
	} 
}
