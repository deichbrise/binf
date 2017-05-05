package com.week3.solution.dynamic;

/**
 * A Class, showing the different abilities of birds in the aviary
 *
 * @author Kevin Trebing
 */
public class Aviary {

   public static void main(String[] args) {
      Dodo dodo = new Dodo();

      // Wir greifen explizit auf die instanzvariable von Bird zu
      System.out.println("1: ((Bird)dodo).ability           : " + ((Bird) dodo).ability);

      // Wir greifen auf die Instanzvariable von Dodo zu
      System.out.println("2: dodo.ability                   : " + dodo.ability);

      // Dynamisches Binden führt dazu, dass die instanzmethode von Dodo verwendet wird, die wiederum auf die
      // Instanzvariable der Superklasse zugreift
      System.out.println("3: dodo.getAbility()              : " + dodo.getAbility());

      Parrot parrot = new Parrot();

      // Greift auf die Instanzmethode von Parrot zu, die auf die Abilities der Superklasse und auf die Instanzvariable
      // von Parrot zugrieft
      System.out.println("4: parrot.allAbilities            : " + parrot.allAbilities());

      // Greift nur auf die Instanzvariable von Parrot zu
      System.out.println("5: parrot.ability                 : " + parrot.ability);

      Bird carsten = new Dodo();

      // Greift auf die Instanzvariable von Bird zu, da für carsten vom Typ Bird ist und kein dynamisches Binden für
      // Instanzvariablen gilt
      System.out.println("6: carsten.ability                : " + carsten.ability);

      // Greift auf die dynamisch gebundene Methode von Bird zu, die wiederum auf die dynamisch gebunde getAbility() Methode
      // Zugreift, die aber durch dynamisches binden zur Dodo Klasse gehört, die dann auf die Instanzvariable von Dodo
      // zugreift
      System.out.println("7: ((Bird)carsten).allAbilities() : " + ((Bird) carsten).allAbilities());

      Bird einstein = parrot;
      System.out.println("8: einstein.allAbilities()        : " + einstein.allAbilities());
      System.out.println("9: einstein.getAbility()          : " + einstein.getAbility());
      System.out.println("10: ((Parrot)einstein).ability    : " + ((Parrot) einstein).ability);
   }
}
