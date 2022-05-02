package model;

/**
 * Enum represent 2 genders
 * @author Duc Anh
 *
 */
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
