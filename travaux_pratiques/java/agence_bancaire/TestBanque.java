import java.util.ArrayList;


public class TestBanque{
  public static void main(String[] args){
    CompteBancaire cb1,cb2,cb3,cb4;
    cb1=new CompteBancaire("toto",3000f,"765TR");
    cb2=new CompteBancaire("tata",3000f,"766TR");
    cb3=new CompteBancaire("titi",3000f,"767TR");
    cb4=new CompteBancaire("tutu",3000f,"768TR");

    AgenceBancaire ab = new AgenceBancaire("BNP", "Villetaneuse");
    ab.add(cb1);
    ab.add(cb2);
    ab.add(cb3);
    ab.add(cb4);
    ab.add(cb4);
    System.out.println(ab.toString());

    System.out.println(ab.compteExiste(cb2));
    System.out.println(ab.comptePersonneExiste("toto"));
    System.out.println(ab.lesComptesDe("tto"));
  }
}
