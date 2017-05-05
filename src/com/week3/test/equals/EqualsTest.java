package com.week3.test.equals;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;
import com.week3.solution.equals.Person;
import com.week3.solution.equals.Student;

/**
 * @author pascalstammer
 * @version 05.05.17.
 */
@Test
public class EqualsTest extends AbstractTest {

    public static void main( String[] args ) {
        EqualsTest test = new EqualsTest();
        test.runAllTests();
    }

    @Test
    public void testPersonHashCode() {
        final Person person1 = new Person( "testPerson1" );
        final Person person2 = new Person( "testPerson2" );

        final int person1HashCode1 = person1.hashCode();
        final int person1HashCode2 = person1.hashCode();

        final int person2HashCode1 = person2.hashCode();
        final int person2HashCode2 = person2.hashCode();

        // Consistent
        Assert.assertEquals(person1HashCode1, person1HashCode2);
        Assert.assertEquals(person2HashCode1, person2HashCode2);

        // In this case person1 and person2 hash code are not equal and person1 and person2 are to different objects
        Assert.assertTrue( person1HashCode1 != person2HashCode1 );

        // Provide no information about values tested in equal method
    }

    @Test
    public void testStudentHashCode() {
        final Student student1 = new Student( "student1", 123 );
        final Student student2 = new Student( "student2", 123 );

        final int student1HashCode1 = student1.hashCode();
        final int student1HashCode2 = student1.hashCode();

        final int student2HashCode1 = student2.hashCode();
        final int student2HashCode2 = student2.hashCode();

        // Consistent
        Assert.assertEquals( student1HashCode1, student1HashCode2 );
        Assert.assertEquals( student2HashCode1, student2HashCode2 );

        // In this case student1 and student2 hash code are not equal and student1 and student2 are to different objects
        Assert.assertTrue( student1HashCode1 != student2HashCode1 );

        final int matNr = student1HashCode1 - student1.getName().hashCode();

        // Problem: HashCode provides information about matriklnummer. We have to hash matrikelnummer as well
        Assert.assertEquals( student1.getMatNr(), matNr );

        // Better hashcode method
        final int hashcodeimprovment = Integer.toString( student1.getMatNr() ).hashCode() + student1.getName().hashCode();
    }

    @Test
    public void testEquals() {
        Person person1 = new Person( "charlie" );
        Person person2 = new Person( "charlie" );
        Person person3 = new Person( "charlie" );

        Assert.assertTrue( genericEqualMethodConventionTest( person1, person2, person3 ) );

        Person person4 = new Person( "charlie" );
        Student person5 = new Student( "charlie", 123 );
        Person person6 = new Person( "charlie" );

        // Violate Symmetric Contract and Transitive Contract
        // Improvement will be equality only by name
        Assert.assertFalse( genericEqualMethodConventionTest( person4, person5, person6 ) );
    }

    protected boolean genericEqualMethodConventionTest(final Object object1, final Object object2, final Object object3) {
        boolean result = true;

        // Reflexive
        result = result && object1.equals( object1 );

        // Symmetric
        if(object1.equals( object2 )) {
            result = result && object2.equals( object1 );
        }
        // Transitive
        if(object1.equals( object2 ) && object2.equals( object3 )) {
            result = result && object1.equals( object3 );
        }

        // Consistent
        if(object1.equals( object2 )) {
            result = result && object1.equals( object2 );
        } else {
            result = result && !object1.equals( object2 );
        }

        // Null
        result = result && !object1.equals( null );
        result = result && !object2.equals( null );
        result = result && !object3.equals( null );

        return result;
    }

}
