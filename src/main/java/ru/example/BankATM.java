package ru.example;

import java.util.Scanner;

public class BankATM {

    public static boolean checkCardAndPassword(String inputCard, int inputPwd, int count) {
        String cardNum = "6228123123";
        int pwd = 888888;

        if (inputCard.equals(cardNum) && inputPwd == pwd) {
            return true;
        } else {
            if (count <= 2) {
                System.out.println("Извините, пароль неверный, у вас все еще есть " + (3 - count) + " Второй шанс!");
            } else {
                System.out.println("Извините, ваша карта заблокирована!");
            }
            return false;
        }
    }

    public static boolean auth() {
        boolean flag = true;

        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);


        for (int i = 1; i <= 3; i++) {
            String inputCard = getText(input, "Пожалуйста, вставьте свою банковскую карту: \n");
            int inputPwd = Integer.parseInt(getInput(input1, "Пожалуйста, введите ваш пароль: \n"));

            flag = checkCardAndPassword(inputCard, inputPwd, i);
            if (flag) {
                break;
            }
        }
        return flag;
    }

    public static String getInput(Scanner in, String prompt) {
        System.out.print(prompt);
        String text = "";
        while (true) {
            text = in.nextLine();
            if (isInteger(text))
                break;
            System.out.print("Попробуйте еще раз, " + prompt);
        }
        return text;
    }

    public static String getText(Scanner in, String prompt) {
        System.out.print(prompt);
        String text = "";
        while (true) {
            text = in.nextLine();
            if (isText(text))
                break;
            System.out.print("Попробуйте еще раз, " + prompt);
        }
        return text;
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isText(String str) {
        try {
            String.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static double withdrawal() {
        Scanner input = new Scanner(System.in);
        double surplus = 1000;// баланс
        double getMoney = input.nextDouble();
        if (getMoney > 0) {
            if (getMoney <= surplus) {
                if (getMoney % 100 == 0) {
                    System.out.println("Пожалуйста, возьмите свою банкноту! Баланс ￥" + (surplus - getMoney));
                } else {
                    System.out.println("Извините, я не могу принять изменения!");
                }
            } else {
                System.out.println("Извините, баланс недостаточен!");
            }
        } else {
            System.out.println("Пожалуйста, введите правильную сумму:");
        }

        return getMoney;
    }


    public static double saveMoney(double surplus) {
        Scanner input = new Scanner(System.in);
        double saveMoney = input.nextDouble();
        if (saveMoney > 0 && saveMoney <= 10000) {
            if (saveMoney % 100 == 0) {
                surplus += saveMoney;
                System.out.println("Депозит успешен! Баланс ￥" + surplus);
            } else {

                double backMoney = saveMoney % 100;
                surplus = saveMoney + surplus - backMoney;
                System.out.println("Депозит успешен! Баланс ￥" + surplus);
                System.out.println("Пожалуйста, заберите сдачу ￥" + backMoney);
            }
        } else if (saveMoney > 10000) {
            System.out.println("Внесите депозит до 10000 юаней за раз, пожалуйста, внесите депозит партиями!");
        } else {
            System.out.println("Депонированная банкнота является поддельной банкнотой, и она недействительна и конфискована!");
        }
        return saveMoney;
    }


    public static double transferMoney(double surplus) {
        Scanner input = new Scanner(System.in);
        double goMoney = input.nextDouble(); // сумма перевода
        if (goMoney > 0) {
            if (goMoney <= surplus) {
                System.out.println("Перевод успешно! Баланс is" + (surplus - goMoney));
            } else {
                System.out.println("Извините, пожалуйста, убедитесь, что у вас достаточно средств на карте!");
            }

        } else {
            System.out.println("Перевод не выполнен! Пожалуйста, введите правильную сумму:");
        }
        return goMoney;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double surplus = 1000;// баланс

        // интерфейс
        System.out.println("--------- Добро пожаловать в банкомат ICBC ---------");

        /** Выберите функцию после успешного входа в систему */
        if (auth()) {
            char answer = 'y';
            while (answer == 'y') {
                System.out.println("Пожалуйста, выберите функцию: 1. Вывод средств 2. Пополнение счета 3. Проверка баланса 4. Перевод 5. Выход");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        // Выполнить операцию снятия
                        System.out.println("---> Вывод средств");
                        System.out.println("Пожалуйста, введите сумму вывода:");
                        withdrawal();
                        break;
                    case 2:
                        // выполнить депозитную операцию
                        System.out.println("---> Депозит");
                        System.out.println("Пожалуйста, расположите банкноты и поместите их в депозитный порт:");
                        saveMoney(surplus);
                        break;
                    case 3:
                        // выполнить запрос баланса
                        System.out.println("---> Проверить баланс");
                        System.out.println("Баланс на вашей карте:" + surplus);
                        break;
                    case 4:
                        // выполнить операцию переноса
                        System.out.println("---> Трансфер");
                        System.out.println("Пожалуйста, введите сумму перевода:");
                        transferMoney(surplus);
                        break;
                    case 5:
                        // выполнить операцию выхода
                        System.out.println("Спасибо за ваше использование!");
                        return;
                    default:
                        System.out.println("Извините, выбранная вами функция неверна!");
                        break;
                }// switch end
                System.out.println("Продолжить? Да / Нет");
                answer = input.next().charAt(0);

            } // while end
            System.out.println("Спасибо за ваше использование!");
        }
    }
}