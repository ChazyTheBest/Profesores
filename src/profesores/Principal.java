package profesores;

import java.util.Arrays;
import java.util.Scanner;

public class Principal
{
    public static void main(String[] args)
    {
        new Principal().go();
    }

    Profesor[] altaProfesor(Profesor[] lista, Profesor p)
    {
        Profesor[] old = lista;

        lista = new Profesor[old.length + 1];

        int num = 0;

        for (Profesor pf : old)
        {
            lista[num] = pf;
            num++;
        }

        lista[num] = p;

        return lista;
    }

    Profesor[] bajaProfesor(Profesor[] lista, int n)
    {
        Profesor[] old = lista;

        lista = new Profesor[old.length - 1];

        int num = 0;

        for (Profesor p : old)
        {
            if (num != n)
            {
                lista[num > n ? num - 1 : num] = p;
            }

            num++;
        }

        return lista;
    }

    String isValidIndex(Profesor[] lista, int index)
    {
        String text;
        int n = lista.length;

        if (n > 0)
        {
            if (index > -1 && index <= (n - 1))
            {
                text = "ok";
            }

            else
            {
                text = "No hay ningún profesor con ese número.";
            }
        }

        else
        {
            text = "No hay ningún profesor creado.";
        }

        return text;
    }

    Profesor[] burbuja(Profesor[] lista)
    {
        int n = lista.length;
        Profesor tmp;

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (lista[j].getNombre().compareTo(lista[i].getNombre()) < 0)
                {
                    tmp = lista[i];
                    lista[i] = lista[j];
                    lista[j] = tmp;
                }
            }
        }

        return lista;
    }

    void go()
    {
        Profesor[] lista = new Profesor[0];
        Scanner sc = new Scanner(System.in);

        System.out.print("Curso: ");
        Profesor.setCurso(sc.nextLine());

        System.out.print("\nImporte Horas Extra: ");
        while (!sc.hasNextDouble()) sc.next();
        Profesor.setPagoPorHoraExtra(sc.nextDouble());

        int option = -1;

        while (option != 0)
        {
            System.out.println("\n\n\tS E L E C C I O N E   U N A   O P C I O N\n");
            System.out.println("\t1. ALTA DE UN PROFESOR");
            System.out.println("\t2. BAJA DE UN PROFESOR");
            System.out.println("\t3. CONSULTA DE DATOS PERSONALES DE UN PROFESOR");
            System.out.println("\t4. INTRODUCIR HORAS EXTRAORDINARIAS DE UN MES");
            System.out.println("\t5. LISTADO DE PROFESORES");
            System.out.println("\t6. LISTADO DE NOMINAS DE UN MES");
            System.out.println("\t7. ORDENAR POR NOMBRE UTILIZANDO EL MÉTODO DE LA BURBUJA");
            System.out.println("\t8. ORDENAR POR DNI UTILIZANDO EL INTERFACE COMPARABLE");
            System.out.println("\t0. SALIR DEL PROGRAMA");
            System.out.print("\n\n\nOPCION SELECCIONADA: ");

            if (sc.hasNextInt())
            {
                String text;
                option = sc.nextInt();

                switch (option)
                {
                    case 0: break;
                    case 1: // new professor
                    {
                        Profesor p = new Profesor();

                        p.nuevoProfesor(sc);

                        lista = altaProfesor(lista, p);

                        break;
                    }
                    case 2: // remove professor
                    {
                        System.out.print("Número del profesor a eliminar: ");
                        while (!sc.hasNextInt()) sc.next();
                        int num = sc.nextInt() - 1; // people normally hates 0, so easy fix

                        text = isValidIndex(lista, num);

                        if (text == "ok")
                        {
                            lista = bajaProfesor(lista, num);
                            text = String.format("El profesor número %d ha sido eliminado.", num + 1); // consistent numbers
                        }

                        System.out.println(text);

                        break;
                    }
                    case 3: // print professor data
                    {
                        System.out.print("Número del profesor a consultar: ");
                        while (!sc.hasNextInt()) sc.next();
                        int num = sc.nextInt() - 1; // people normally hates 0, so easy fix

                        text = isValidIndex(lista, num);

                        if (text == "ok")
                        {
                            text = lista[num].imprimeProfesor();
                        }

                        System.out.println(text);

                        break;
                    }
                    case 4: // enter extra hours done by all professor for a given month
                    {
                        System.out.print("Horas extraordinarias realizadas por los profesores en el mes [1-12]: ");
                        while (!sc.hasNextInt()) sc.next();
                        int mes = sc.nextInt() - 1; // array index starts at 0

                        if (mes > -1 && mes < 12)
                        {
                            text = isValidIndex(lista, 0);

                            if (text == "ok")
                            {
                                for (Profesor p : lista)
                                {
                                    System.out.printf("\nNombre profesor: %s\nHoras realizadas: ", p.getNombre());
                                    while (!sc.hasNextInt()) sc.next();
                                    p.setHorasExtra(mes, sc.nextInt());
                                }
                            }

                            else
                            {
                                System.out.println(text);
                            }
                        }

                        else
                        {
                            System.out.println("El mes debe ser un número entre 1 y 12");
                        }

                        break;
                    }
                    case 5: // print every professor's data
                    {
                        int num = 1;

                        text = isValidIndex(lista, 0);

                        if (text == "ok")
                        {
                            text = "";

                            for (Profesor p : lista)
                            {
                                text += String.format("\nNúmero profesor: %d\n%s\n", num, p.imprimeProfesor());

                                num++;
                            }
                        }

                        System.out.println(text);

                        break;
                    }
                    case 6: // print all payrolls for a given month
                    {
                        System.out.print("Nóminas del mes: ");
                        while (!sc.hasNextInt()) sc.next();
                        int mes = sc.nextInt() - 1, // array index starts at 0
                            num = 1;

                        text = isValidIndex(lista, 0);

                        if (text == "ok")
                        {
                            text = "";

                            for (Profesor p : lista)
                            {
                                text += String.format("\nNúmero profesor: %d\n%s\n", num, p.imprimirNominas(mes));

                                num++;
                            }
                        }

                        System.out.println(text);

                        break;
                    }
                    case 7:
                    {
                        lista = burbuja(lista);

                        break;
                    }
                    case 8:
                    {
                        Arrays.sort(lista);

                        break;
                    }
                    default:
                    {
                        System.out.println("La opcion debe ser un numero entre 0 y 6.");
                    }
                }
            }

            else
            {
                System.out.println("La opcion debe ser un numero entre 0 y 6.");
                sc.next();
            }
        }

        sc.close();
    }
}
