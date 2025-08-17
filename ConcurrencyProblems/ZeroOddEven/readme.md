🧐Question :
You have a function printNumber that can be called with an integer parameter and prints it to the console.

‍

• For example, calling printNumber(7) prints 7 to the console.

You are given an instance of the class ZeroEvenOdd that has three functions: zero, even, and odd.

‍

‍The same instance of ZeroEvenOdd will be passed to three different threads:

• Thread A: calls zero() that should only output 0's.

• Thread B: calls even() that should only output even numbers.

• Thread C: calls odd() that should only output odd numbers.

Modify the given class to output the series "010203040506..." where the length of the series must be 2n.

‍

Implement the ZeroEvenOdd class:

• ZeroEvenOdd(int n) Initializes the object with the number n that represents the numbers that should be printed.

• void zero(printNumber) Calls printNumber to output one zero.

• void even(printNumber) Calls printNumber to output one even number.

• void odd(printNumber) Calls printNumber to output one odd number.

‍

🚧 Constraints: 
▫️1 <= n <= 1000
Example 1 : 
Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously.
One of them calls zero(), the other calls even(), and the last one calls odd().
"0102" is the correct output.
	
Example 2 : 
Input: n = 5	
Output: "0102030405"
‍

🔗 Problem link : https://leetcode.com/problems/print-zero-even-odd/description/

‍

Level : Medium
Topics : Concurrency 
Companies : Goldman Sachs, Microsoft
‍‍

💡Solution :
The key challenge in this problem is synchronizing the three threads to ensure they execute in the correct order. We need to make sure that:

1. A zero is printed before each number

2. Odd and even numbers are printed in sequence

‍

We can use semaphores to control the execution flow. Semaphores are perfect for this kind of synchronization as they allow us to signal between threads.

‍

🧩 Approach 1 : Using Semaphores for Thread Synchronization
🧠 Intuition :

• We'll use three semaphores: one for the zero thread, one for the even thread, and one for the odd thread.
• The zero thread will alternate between signaling the odd and even threads.
• The odd and even threads will signal the zero thread after printing their respective numbers.
‍

🤖 Algorithm :
1. Initialize three semaphores: 

• zeroSemaphore with initial permit of 1 (allowing the zero thread to run first)

• evenSemaphore with initial permit of 0 (blocking the even thread initially)

• oddSemaphore with initial permit of 0 (blocking the odd thread initially)

‍

2. In the zero() method: 

• For each number from 1 to n: 

• Acquire zeroSemaphore to ensure exclusive access

• Print zero

• Release either oddSemaphore or evenSemaphore based on the next number

• Toggle between odd and even for the next iteration

‍

3. In the even() method: 

• For each even number from 2 to n (increment by 2): 

• Acquire evenSemaphore to wait for the zero thread's signal

• Print the even number

• Release zeroSemaphore to allow the zero thread to continue

‍

4. In the odd() method: 

• For each odd number from 1 to n (increment by 2): 

• Acquire oddSemaphore to wait for the zero thread's signal

• Print the odd number

• Release zeroSemaphore to allow the zero thread to continue