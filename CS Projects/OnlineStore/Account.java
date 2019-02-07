public class Account
{
  double balance = 0.00;
  String name = "";
  String username = "";
  String hashed = "";
  
  Account(double balance, String name, String username, String hashed)
  {
    this.balance = balance;
    this.name = name;
    this.username = username;
    this.hashed = hashed;
  }
}