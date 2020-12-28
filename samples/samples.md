Using BatakJava, we will show how we deal with changes that happen during software evolution.

## Parameter Changes

To show the changes, we will use four different classes `C`, `D`, `E`, and `F`. The classes `C` and `D` belong to the 
package `top`, the class  `E` belongs to the package `mid`, and the class `F` belongs to the package `down`. 

In the package `top`, the class `C` consists of two versions, with different implementations, the class `C` consists of 
two versions with similar implementations.

In the package `mid`, the class `E` consists of one version that depends on the first version of the class `C`.

In the package `down`, the class `F` consists of one version that depends the class `E` and also the second version of 
the class `C`.

The dependency relation is illustrated below:

````
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TOP

  ---------               ---------
 | class D |   update    | class D |
 |  ver.1  |  ========>  |  ver.2  |
  ---------               ---------
  
  ---------               ---------
 | class C |   update    | class C |
 |  ver.1  |  ========>  |  ver.2  |
  ---------               ---------
     |                        |
~~~~~~\~~~~~~~~~~~~~~~~~~~~~~~|~~~~~~~~~~~~~MID
       |                      |
       v                      |
    ---------                 |
   | class E |                |
    ---------                 |
          |                   |
~~~~~~~~~~~\~~~~~~~~~~~~~~~~~~|~~~~~~~~~~~~~DOWN
            \_____            |
                  \    ______/
                   |  |
                   v  v
                --------- 
               | class F |
                --------- 

````


### Constructors

````
package constructors.top ver 1;
public class C {
  private float primitiveField;
  public D classField;
  public C(float primitiveField, D classField) {
    this.primitiveField = primitiveField;
    this.classField = classField;
  }
}
````