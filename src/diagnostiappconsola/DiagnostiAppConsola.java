/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnostiapppc;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Versión de consola del DiagnostiAppPC.
 * - Menús con validación
 * - ASCII logo incluido (siempre se muestra)
 * - Opción para abrir un archivo logo.png desde /resources/logo.png (si exists)
 *
 * Diseñado para ser entendido sin POO avanzada (uso de métodos estáticos).
 */
public class DiagnostiAppConsola {

    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Map<String, String>> diagnosticos = new LinkedHashMap<>();

    public static void main(String[] args) {
        inicializarDiagnosticos();
        mostrarLogoASCII();
        mostrarMenuPrincipal();
        sc.close();
    }

    // -------------------------
    // Inicialización de datos
    // -------------------------1
    private static void inicializarDiagnosticos() {
        /*Map<String, String> noEnciende = new LinkedHashMap<>();
        noEnciende.put("🔌 Verificar cable de alimentación", "👉 Asegúrate de que el cable esté bien conectado y el enchufe funcione.");
        noEnciende.put("🔋 Revisar batería o cargador", "👉 Si es laptop, prueba con otro cargador o revisa el estado de la batería.");
        noEnciende.put("⚙️ Revisar fuente de poder", "⚠ Si todo parece correcto y aún no enciende, la fuente de poder puede estar dañada.");
        diagnosticos.put("El equipo no enciende", noEnciende);*/
        Map<String, String> noEnciende = new LinkedHashMap<>();
        noEnciende.put("🔌 Verificar cable de alimentación", "👉 Asegúrate de que el cable esté bien conectado y el enchufe funcione.");
        noEnciende.put("🔋 Revisar batería o cargador", "👉 Si es laptop, prueba con otro cargador o revisa el estado de la batería.");
        noEnciende.put("⚙️ Revisar fuente de poder", "⚠ Si todo parece correcto y aún no enciende, la fuente de poder puede estar dañada.");

        String[] funcionalidadesComunes = {
            "Verificar que enchufes y tomas estén conectados y en buen estado.",
            "Verifica los cables del computador estén conectados y en buen estado.",
            "Preguntar si el estado fue manipulado por otra persona.",
            "Registrar cualquier observación antes de continuar."
        };

        Map<String, String> noEnciendeConComunes = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : noEnciende.entrySet()) {
            String descripcionCompleta = entry.getValue() + "\n";
            for (String comun : funcionalidadesComunes) {
                descripcionCompleta += "-> " + comun + "\n";
            }
            noEnciendeConComunes.put(entry.getKey(), descripcionCompleta);
        }

        diagnosticos.put("El equipo no enciende", noEnciendeConComunes);

        Map<String, String> noImagen = new LinkedHashMap<>();
        noImagen.put("💾 Verificar memoria RAM", "👉 Asegúrate de que los módulos estén bien insertados en las ranuras.\n⚠ Hazlo con cuidado, evita tocar los conectores dorados.");
        noImagen.put("🖥️ Revisar cable de monitor", "👉 Verifica la conexión del monitor o prueba con otro cable/puerto.");
        noImagen.put("🎮 Comprobar tarjeta gráfica", "⚠ Si el problema persiste, puede ser la tarjeta gráfica o la placa base.");
        diagnosticos.put("Enciende pero no muestra imagen", noImagen);

        Map<String, String> lento = new LinkedHashMap<>();
        lento.put("🚫 Cerrar programas innecesarios", "👉 Usa el Administrador de tareas para cerrar procesos pesados.");
        lento.put("🧹 Liberar espacio en disco", "👉 Elimina archivos temporales y desinstala programas no usados.");
        lento.put("⚡ Mejorar hardware", "💡 Añadir más RAM o cambiar a un SSD puede mejorar notablemente el rendimiento.");
        diagnosticos.put("El equipo está muy lento", lento);

        Map<String, String> pitidos = new LinkedHashMap<>();
        pitidos.put("📋 Anotar el patrón de pitidos", "💡 Ejemplo: 1 largo, 2 cortos. Cada BIOS tiene su código de error.");
        pitidos.put("🔍 Buscar significado del código", "👉 Busca en la web el código según el fabricante de tu BIOS (AMI, Award, etc.).");
        diagnosticos.put("Pitidos al encender", pitidos);

        Map<String, String> perifericos = new LinkedHashMap<>();
        perifericos.put("🔌 Reconectar dispositivos", "👉 Desconecta y vuelve a conectar teclado y ratón.");
        perifericos.put("🪫 Revisar baterías o puertos USB", "👉 Si son inalámbricos, cambia baterías. Si son USB, prueba otros puertos.");
        diagnosticos.put("Teclado o ratón no funcionan", perifericos);

        Map<String, String> calor = new LinkedHashMap<>();
        calor.put("🧽 Limpiar ventiladores", "👉 Usa aire comprimido para quitar polvo de rejillas y ventiladores.");
        calor.put("💨 Evitar superficies blandas", "👉 No uses la laptop sobre camas o cojines, bloquean la ventilación.");
        calor.put("🔥 Revisar pasta térmica", "⚠ Si el equipo se apaga solo, el procesador podría estar sobrecalentándose.");
        diagnosticos.put("Sobrecalentamiento", calor);
    }

    // -------------------------
    // Interfaz de consola
    // -------------------------
    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            imprimirLinea();
            System.out.println(" MENÚ PRINCIPAL");
            imprimirLinea();
            System.out.println("1) Listar problemas");
            System.out.println("2) Buscar problema por palabra");
            System.out.println("3) Mostrar/abrir logo del proyecto");
            System.out.println("0) Salir");
            imprimirLinea();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    listarYSeleccionarProblema();
                    break;
                case 2:
                    buscarProblema();
                    break;
                case 3:
                    mostrarLogoASCII();
                    preguntarYAbrirLogoImagen();
                    break;
                case 0:
                    System.out.println("\n¡Gracias por usar DiagnostiAppConsola! 👋");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 0);
    }

    // Lista problemas y permite seleccionar uno
    private static void listarYSeleccionarProblema() {
        String[] claves = diagnosticos.keySet().toArray(new String[0]);
        if (claves.length == 0) {
            System.out.println("No hay problemas registrados.");
            return;
        }

        System.out.println("\nLista de problemas:");
        for (int i = 0; i < claves.length; i++) {
            System.out.printf("%d) %s%n", (i + 1), claves[i]);
        }
        System.out.println("0) Volver al menú principal");

        int sel = leerEntero("Selecciona el número del problema: ");
        if (sel == 0) return;
        if (sel < 1 || sel > claves.length) {
            System.out.println("Selección fuera de rango.");
            return;
        }
        mostrarOpcionesDelProblema(claves[sel - 1]);
    }

    // Buscar problema por palabra clave
    private static void buscarProblema() {
        System.out.print("\nIngresa palabra para buscar (ej: 'imagen', 'lento'): ");
        String term = sc.nextLine().trim().toLowerCase();
        if (term.isEmpty()) {
            System.out.println("No ingresaste nada.");
            return;
        }
        int i = 1;
        String[] claves = diagnosticos.keySet().toArray(new String[0]);
        Map<Integer, String> coincidencias = new LinkedHashMap<>();
        for (String clave : claves) {
            if (clave.toLowerCase().contains(term)) {
                coincidencias.put(i++, clave);
            }
        }
        if (coincidencias.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
            return;
        }
        System.out.println("\nCoincidencias:");
        for (Map.Entry<Integer, String> e : coincidencias.entrySet()) {
            System.out.printf("%d) %s%n", e.getKey(), e.getValue());
        }
        int sel = leerEntero("Selecciona el número de la coincidencia (0 para volver): ");
        if (sel == 0) return;
        String problema = coincidencias.get(sel);
        if (problema != null) mostrarOpcionesDelProblema(problema);
        else System.out.println("Selección inválida.");
    }

    // Muestra los pasos/soluciones de un problema y permite ver detalles
    private static void mostrarOpcionesDelProblema(String problema) {
        Map<String, String> pasos = diagnosticos.get(problema);
        if (pasos == null || pasos.isEmpty()) {
            System.out.println("No hay soluciones registradas para este problema.");
            return;
        }
        System.out.println("\n--- " + problema + " ---");
        String[] claves = pasos.keySet().toArray(new String[0]);
        for (int i = 0; i < claves.length; i++) {
            System.out.printf("%d) %s%n", (i + 1), claves[i]);
        }
        System.out.println("0) Volver");

        int sel = leerEntero("Selecciona una solución para ver detalles: ");
        if (sel == 0) return;
        if (sel < 1 || sel > claves.length) {
            System.out.println("Selección fuera de rango.");
            return;
        }
        System.out.println("\nDetalle:");
        System.out.println(pasos.get(claves[sel - 1])); // imprime la descripción
        esperarEnterParaContinuar();
    }

    // -------------------------
    // UTILIDADES y entrada
    // -------------------------
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Ingresa un número.");
            }
        }
    }

    private static void imprimirLinea() {
        System.out.println("========================================");
    }

    private static void esperarEnterParaContinuar() {
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // -------------------------
    // Logo ASCII y abrir imagen
    // -------------------------
    private static void mostrarLogoASCII() {
        System.out.println();
        System.out.println("  ____  _       _                _  _  __    ___   ___ ");
        System.out.println(" |  _ \\(_) __ _| |__   ___  _ __| || |/ /   / _ \\ / _ \\");
        System.out.println(" | | | | |/ _` | '_ \\ / _ \\| '__| || ' /   | | | | | | |");
        System.out.println(" | |_| | | (_| | | | | (_) | |  |__   <    | |_| | |_| |");
        System.out.println(" |____/|_|\\__, |_| |_|\\___/|_|     |_|\\_\\    \\___/ \\___/ ");
        System.out.println("          |___/   DiagnostiAppConsola - Diagnóstico básico");
        System.out.println();
    }

    private static void preguntarYAbrirLogoImagen() {
        System.out.println("¿Quieres intentar abrir el archivo de imagen logo.png desde resources/?");
        System.out.println("Esto intentará abrir el archivo con el visor de imágenes del sistema (si existe).");
        System.out.print("s = sí / otra tecla = no : ");
        String r = sc.nextLine().trim().toLowerCase();
        if ("s".equals(r) || "si".equals(r)) {
            abrirLogoImagen();
        } else {
            System.out.println("Ok, no se abrirá la imagen.");
        }
    }

    private static void abrirLogoImagen() {
        // Ruta recomendada: carpeta del proyecto -> resources/logo.png
        String ruta = "resources/logo.png";
        File f = new File(ruta);
        if (!f.exists()) {
            System.out.println("No se encontró " + ruta + ". Coloca tu imagen en la carpeta del proyecto -> resources/logo.png");
            return;
        }
        if (!Desktop.isDesktopSupported()) {
            System.out.println("La plataforma no soporta abrir archivos automáticamente.");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(f);
            System.out.println("Abriendo " + ruta + " ...");
        } catch (IOException ex) {
            System.out.println("No se pudo abrir el archivo: " + ex.getMessage());
        }
    }
}
