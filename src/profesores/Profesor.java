package profesores;

import java.util.Locale;
import java.util.Scanner;
import java.time.Month;
import java.time.format.TextStyle;

public class Profesor implements Comparable<Profesor>
{
    private static String curso = "";
    private static double pagoPorHoraExtra = 0;

    private String dni = "", nombre = "";
    private double sueldoBase = 0, tipoIRPF = 0;
    private int[] horasExtra = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    public void nuevoProfesor(Scanner sc)
    {
        sc.nextLine();

        System.out.print("Profesor: ");
        nombre = sc.nextLine();

        System.out.print("DNI: ");
        dni = sc.nextLine();

        System.out.print("Sueldo Base: ");
        while (!sc.hasNextDouble()) sc.next();
        sueldoBase = sc.nextDouble();

        System.out.print("Tipo de IRPF: ");
        while (!sc.hasNextDouble()) sc.next();
        tipoIRPF = sc.nextDouble();

        horasExtra = new int[12];
    }

    private double calcularImporteHorasExtras(int mes)
    {
        return horasExtra[mes] * pagoPorHoraExtra;
    }

    private double calcularSueldoBruto(int mes)
    {
        return sueldoBase + calcularImporteHorasExtras(mes);
    }

    private double calcularRetencionesIrpf(int mes)
    {
        return (calcularSueldoBruto(mes) * tipoIRPF) / 100;
    }

    private double calcularSueldo(int mes)
    {
        return calcularSueldoBruto(mes) - calcularRetencionesIrpf(mes);
    }

    public String imprimeProfesor()
    {
        return String.format("Nombre: %s\nDNI: %s\nSueldo Base: %.2f\nTipo de IRPF: %.2f\n", nombre, dni, sueldoBase, tipoIRPF);
    }

    public String imprimirNominas(int mes)
    {
        return String.format("Nombre: %s\nDNI: %s\nCurso: %s   Nómina mes: %s\nSueldo Base: %.2f\nHoras Extra: %d\nTipo de IRPF: %.2f\nSueldo Bruto: %.2f\nRetención por IRPF: %.2f\nSueldo Neto: %.2f\n",
                              nombre, dni, curso, Month.of(mes + 1).getDisplayName(TextStyle.FULL, Locale.getDefault()), sueldoBase,
                              horasExtra[mes], tipoIRPF, calcularSueldoBruto(mes), calcularRetencionesIrpf(mes), calcularSueldo(mes));
    }

    @Override
    public int compareTo(Profesor p)
    {
        return dni.compareTo(p.dni);
    }

    public static String getCurso()
    {
        return curso;
    }

    public static void setCurso(String curso)
    {
        Profesor.curso = curso;
    }

    public static double getPagoPorHoraExtra()
    {
        return pagoPorHoraExtra;
    }

    public static void setPagoPorHoraExtra(double pagoPorHoraExtra)
    {
        Profesor.pagoPorHoraExtra = pagoPorHoraExtra;
    }

    public String getDni()
    {
        return dni;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public double getSueldoBase()
    {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase)
    {
        this.sueldoBase = sueldoBase;
    }

    public double getTipoIRPF()
    {
        return tipoIRPF;
    }

    public void setTipoIRPF(double tipoIRPF)
    {
        this.tipoIRPF = tipoIRPF;
    }

    public int[] getHorasExtra()
    {
        return horasExtra;
    }

    public void setHorasExtra(int index, int horasExtra)
    {
        this.horasExtra[index] = horasExtra;
    }
}
