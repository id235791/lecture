using System;
using System.Globalization;

namespace Project01
{
    class Program
    {
        Random r = new Random();
        int Dice()
        {
            return r.Next(6)+1;
        }
        double MyLife(int sum)
        {
            return sum - 18.0;
        }
        string ToDollar(double money)
        {
            return money.ToString("C1", CultureInfo.CreateSpecificCulture("en-US"));
        }
        static void Main(string[] args)
        {
            Program p = new Program();
            while (true)
            {
                Console.Write("돈걸기? (Y/N) ");
                string choice=Console.ReadLine();
                if (choice.Equals("Y", StringComparison.OrdinalIgnoreCase))
                {
                    int sum = 0;
                    for(int i = 0; i < 5; i++)
                    {
                        sum += p.Dice();
                    }

                    Console.WriteLine("획득 : {0}", p.ToDollar(sum + 0.0));
                    Console.WriteLine("손익 : {0}", p.ToDollar(p.MyLife(sum)));
                }
                else if (choice.Equals("N", StringComparison.OrdinalIgnoreCase)){
                    break;
                }
                else
                {
                    Console.WriteLine("잘못 입력하셨습니다.");
                }
            }
        }
    }
}
