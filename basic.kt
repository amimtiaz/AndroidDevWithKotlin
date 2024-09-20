// Classes and Objects
class Car(val brand: String, var year: Int) {
    // Member function
    fun startEngine() {
        println("Engine started!")
    }
}

fun main() {
    val myCar = Car("Toyota", 2021)
    println(myCar.brand) // Accessing property
    myCar.startEngine()   // Calling function
}


// Constructors
class Person(val name: String, var age: Int)

class Person {
    var name: String
    var age: Int
    
    // Secondary constructor
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}


// Inheritance
// Base class (must be open to be inherited)
open class Animal(val name: String) {
    open fun makeSound() {
        println("Animal sound")
    }
}

// Derived class
class Dog(name: String): Animal(name) {
    override fun makeSound() {
        println("Bark!")
    }
}

fun main() {
    val myDog = Dog("Buddy")
    myDog.makeSound() // Output: Bark!
}

// Abstract Classes and Methods
abstract class Shape {
    abstract fun area(): Double
}

class Circle(val radius: Double) : Shape() {
    override fun area(): Double {
        return Math.PI * radius * radius
    }
}

fun main() {
    val circle = Circle(5.0)
    println("Area of the circle: ${circle.area()}")
}


// Interfaces
interface Drivable {
    fun drive()
}

class Car : Drivable {
    override fun drive() {
        println("Driving the car")
    }
}

fun main() {
    val myCar = Car()
    myCar.drive()  // Output: Driving the car
}


// Visibility Modifiers
class BankAccount(private var balance: Double) {
    fun deposit(amount: Double) {
        balance += amount
    }

    fun checkBalance() {
        println("Current balance: $balance")
    }
}

fun main() {
    val account = BankAccount(100.0)
    account.deposit(50.0)
    account.checkBalance()  // Output: Current balance: 150.0
    // account.balance is not accessible due to private modifier
}


// Data Classes
data class User(val name: String, val age: Int)

fun main() {
    val user1 = User("Alice", 30)
    val user2 = user1.copy(age = 31)
    
    println(user1)  // Output: User(name=Alice, age=30)
    println(user2)  // Output: User(name=Alice, age=31)
}


// Companion Objects
class MathUtil {
    companion object {
        fun add(a: Int, b: Int): Int {
            return a + b
        }
    }
}

fun main() {
    println(MathUtil.add(5, 10))  // Output: 15
}
