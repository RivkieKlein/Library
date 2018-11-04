package library;
import java.time.*;

//class with all properties of college degree
public class EarnedDegree {
DegreeType degreeType;
LocalDate dateEarned;
String school;

EarnedDegree(DegreeType degree, LocalDate dateEarned, String school){
	this.degreeType=degree;
	this.dateEarned=dateEarned;
	this.school=school;
}

@Override
public String toString(){
	StringBuilder output=new StringBuilder(degreeType+" "+dateEarned.getMonth()+" "+dateEarned.getYear()+", "+school);
return output.toString();	
}
}
