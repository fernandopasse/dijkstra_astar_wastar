package trabalho;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Relatorio {

    long tempo;
    int nos_exp;
    float custo;
    int xInicial, yInicial, xFinal, yFinal;
    String desenho;
    static int cont = 0;
    String arquivo;

    public Relatorio(String arquivo) {
        this.arquivo = arquivo;
    }

    public Relatorio(int xInicial, int yInicial, int xFinal, int yFinal, long tempo, int nos_exp, float custo, String arquivo, String desenho) {
        this.tempo = tempo;
        this.nos_exp = nos_exp;
        this.custo = custo;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.arquivo = arquivo;
        this.desenho = desenho;
    }

    public void apaga_arquivo() {
        try {
            File f = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            f.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gera_head() {
        try {
            File relatorio = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            if (!relatorio.exists()) {
                relatorio.createNewFile();
            }
            BufferedWriter gravar = new BufferedWriter(new FileWriter(relatorio, true));
            gravar.newLine();
            gravar.write("<html>");
            gravar.newLine();
            gravar.write("	<head>");
            gravar.newLine();
            gravar.write("		<meta charset=\"utf-8\">");
            gravar.newLine();
            gravar.write("		<script src=\"script/jquery-2.1.3.min.js\"></script>");
            gravar.newLine();
            gravar.write("		<script src=\"script/jquery.dataTables.min.js\"></script>");
            gravar.newLine();
            gravar.write("		<link rel=\"stylesheet\" href=\"css/jquery.dataTables.css\">");
            gravar.newLine();
            gravar.write("		<title>Relatório do algoritmo de Dijkstra</title>");
            gravar.newLine();
            gravar.write("	    <script>");
            gravar.newLine();
            gravar.write("$(document).ready(function() {");
            gravar.newLine();
            gravar.write("jQuery.fn.dataTable.Api.register( 'sum()', function ( ) {"
                    + "	return this.flatten().reduce( function ( a, b ) {"
                    + "		if ( typeof a === 'string' ) {"
                    + "			a = a.replace(/[^\\d.-]/g, '') * 1;"
                    + "		}"
                    + "		if ( typeof b === 'string' ) {"
                    + "			b = b.replace(/[^\\d.-]/g, '') * 1;"
                    + "		}"
                    + ""
                    + "		return a + b;"
                    + "	}, 0 );"
                    + "} );");
            gravar.newLine();
            gravar.write("	$('#relatorio_inc').dataTable( {");
            gravar.newLine();
            gravar.write("		\"language\": {");
            gravar.newLine();
            gravar.write("			\"lengthMenu\": \"Mostrar _MENU_ dados por página\",");
            gravar.newLine();
            gravar.write("                      \"search\":         \"Pesquisar:\",");
            gravar.newLine();
            gravar.write("                      \"paginate\": {");
            gravar.newLine();
            gravar.write("                          \"first\":      \"Primeira\",");
            gravar.newLine();
            gravar.write("                          \"last\":       \"Última\",");
            gravar.newLine();
            gravar.write("                          \"next\":       \"Próxima\",");
            gravar.newLine();
            gravar.write("                          \"previous\":   \"Anterior\"");
            gravar.newLine();
            gravar.write("                      },");
            gravar.newLine();
            gravar.write("			\"zeroRecords\": \"Dados não encontrados - sorry :/\",");
            gravar.newLine();
            gravar.write("			\"info\": \"Mostrando _PAGE_ de _PAGES_\",");
            gravar.newLine();
            gravar.write("			\"infoEmpty\": \"Sem dados disponíveis\",");
            gravar.newLine();
            gravar.write("			\"infoFiltered\": \"(foram filtrados _MAX_ do total de dados)\"");
            gravar.newLine();
            gravar.write("		}");
            gravar.newLine();
            gravar.write("	} );");
            gravar.newLine();
            gravar.write("	$('#relatorio_incaestrela').dataTable( {");
            gravar.newLine();
            gravar.write("		\"language\": {");
            gravar.newLine();
            gravar.write("			\"lengthMenu\": \"Mostrar _MENU_ dados por página\",");
            gravar.newLine();
            gravar.write("                      \"search\":         \"Pesquisar:\",");
            gravar.newLine();
            gravar.write("                      \"paginate\": {");
            gravar.newLine();
            gravar.write("                          \"first\":      \"Primeira\",");
            gravar.newLine();
            gravar.write("                          \"last\":       \"Última\",");
            gravar.newLine();
            gravar.write("                          \"next\":       \"Próxima\",");
            gravar.newLine();
            gravar.write("                          \"previous\":   \"Anterior\"");
            gravar.newLine();
            gravar.write("                      },");
            gravar.newLine();
            gravar.write("			\"zeroRecords\": \"Dados não encontrados - sorry :/\",");
            gravar.newLine();
            gravar.write("			\"info\": \"Mostrando _PAGE_ de _PAGES_\",");
            gravar.newLine();
            gravar.write("			\"infoEmpty\": \"Sem dados disponíveis\",");
            gravar.newLine();
            gravar.write("			\"infoFiltered\": \"(foram filtrados _MAX_ do total de dados)\"");
            gravar.newLine();
            gravar.write("		}");
            gravar.newLine();
            gravar.write("	} );");
            gravar.write("	$('#relatorio_incwaestrela').dataTable( {");
            gravar.newLine();
            gravar.write("		\"language\": {");
            gravar.newLine();
            gravar.write("			\"lengthMenu\": \"Mostrar _MENU_ dados por página\",");
            gravar.newLine();
            gravar.write("                      \"search\":         \"Pesquisar:\",");
            gravar.newLine();
            gravar.write("                      \"paginate\": {");
            gravar.newLine();
            gravar.write("                          \"first\":      \"Primeira\",");
            gravar.newLine();
            gravar.write("                          \"last\":       \"Última\",");
            gravar.newLine();
            gravar.write("                          \"next\":       \"Próxima\",");
            gravar.newLine();
            gravar.write("                          \"previous\":   \"Anterior\"");
            gravar.newLine();
            gravar.write("                      },");
            gravar.newLine();
            gravar.write("			\"zeroRecords\": \"Dados não encontrados - sorry :/\",");
            gravar.newLine();
            gravar.write("			\"info\": \"Mostrando _PAGE_ de _PAGES_\",");
            gravar.newLine();
            gravar.write("			\"infoEmpty\": \"Sem dados disponíveis\",");
            gravar.newLine();
            gravar.write("			\"infoFiltered\": \"(foram filtrados _MAX_ do total de dados)\"");
            gravar.newLine();
            gravar.write("		}");
            gravar.newLine();
            gravar.write("	} );");
            gravar.newLine();
            gravar.write("var table_dij = $('#relatorio_inc').DataTable();\n"
                    + "var soma = table_dij.column( 2 ).data().sum();\n"
                    + "var soma_2 = table_dij.column( 3 ).data().sum();\n"
                     + "var custo = table_dij.column( 4 ).data().sum();\n"
                    + "var media_dij = soma/table_dij.rows().data().length;\n"
                    + "var media_dij2 = soma_2/table_dij.rows().data().length;\n"
                    + "var media_custo = custo/table_dij.rows().data().length;\n"
                    + "$('#dij').html(soma);\n"
                    + "$('#dij_no').html(soma_2);"
                    + "$('#dij_custo').html(custo);"
                    + "$('#dij_customedia').html(media_custo);"
                    
                    + "var table_a = $('#relatorio_incaestrela').DataTable();\n"
                    + "var soma = table_a.column( 2 ).data().sum();\n"
                    + "var soma_2 = table_a.column( 3 ).data().sum();\n"
                     + "var custo = table_a.column( 4 ).data().sum();\n"
                    + "var media_a = soma/table_a.rows().data().length;\n"
                    + "var media_a2 = soma_2/table_a.rows().data().length;\n"
                    + "var media_custo = custo/table_a.rows().data().length;\n"
                    + "$('#a').html(soma);\n"
                    + "$('#a_no').html(soma_2);"
                    + "$('#a_custo').html(custo);"
                    + "$('#a_customedia').html(media_custo);"
                    
                    + "var table_wa = $('#relatorio_incwaestrela').DataTable();\n"
                    + "var soma = table_wa.column( 2 ).data().sum();\n"
                    + "var soma_2 = table_wa.column( 3 ).data().sum();\n"
                     + "var custo = table_wa.column( 4 ).data().sum();\n"
                    + "var media_wa = soma/table_wa.rows().data().length;\n"
                    + "var media_wa2 = soma_2/table_wa.rows().data().length;\n"
                    + "var media_custo = custo/table_wa.rows().data().length;\n"
                    + "$('#wa').html(soma);"
                    + "$('#wa_no').html(soma_2);"
                    + "$('#wa_custo').html(custo);"
                    + "$('#wa_customedia').html(media_custo);"
                    
                    + "$('#dij_m').html(media_dij.toFixed(2));\n"
                    + "$('#a_m').html(media_a.toFixed(2));\n"
                    + "$('#wa_m').html(media_wa.toFixed(2));"
                    
                    + "$('#dij_me').html(media_dij2.toFixed(2));\n"
                    + "$('#a_me').html(media_a2.toFixed(2));\n"
                    + "$('#wa_me').html(media_wa2.toFixed(2));");
            gravar.write("} );");
            gravar.newLine();
            gravar.write("        </script>");
            gravar.newLine();
            gravar.write("	</head>");
            gravar.newLine();
            gravar.write("	<body>");
            gravar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gera_texto(String valor) {
        try {
            File relatorio = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            if (!relatorio.exists()) {
                relatorio.createNewFile();
            }
            BufferedWriter gravar = new BufferedWriter(new FileWriter(relatorio, true));
            gravar.newLine();
            gravar.write(valor);
            gravar.newLine();
            gravar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gera_grafico() {
        try {
            File relatorio = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            if (!relatorio.exists()) {
                relatorio.createNewFile();
            }
            BufferedWriter gravar = new BufferedWriter(new FileWriter(relatorio, true));
            gravar.newLine();
            gravar.newLine();
            gravar.write("<h2>");
            //gravar.write(valor);
            gravar.write("</h2>");
            gravar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gera_titulo(String valor) {
        try {
            File relatorio = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            if (!relatorio.exists()) {
                relatorio.createNewFile();
            }
            BufferedWriter gravar = new BufferedWriter(new FileWriter(relatorio, true));
            gravar.newLine();
            gravar.newLine();
            gravar.write("<h2>");
            gravar.write(valor);
            gravar.write("</h2>");
            gravar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gera_prox(String n) {
        try {
            File relatorio = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            if (!relatorio.exists()) {
                relatorio.createNewFile();
            }
            BufferedWriter gravar = new BufferedWriter(new FileWriter(relatorio, true));
            gravar.newLine();
            gravar.write("		<table id=\"relatorio_inc" + n + "\">");
            gravar.newLine();
            gravar.write("			<thead>");
            gravar.newLine();
            gravar.write("				<tr>");
            gravar.newLine();
            gravar.write("					<th>Estado inicial</th>");
            gravar.newLine();
            gravar.write("					<th>Estado final</th>");
            gravar.newLine();
            gravar.write("					<th>Tempo</th>");
            gravar.newLine();
            gravar.write("					<th>Número de nós expandidos</th>");
            gravar.newLine();
            gravar.write("					<th>Custo</th>");
            gravar.newLine();
            gravar.write("					<th>Desenho</th>");
            gravar.newLine();
            gravar.write("				</tr>");
            gravar.newLine();
            gravar.write("			</thead>");
            gravar.newLine();
            gravar.write("			<tbody>");
            gravar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gerar_body() {
        try {
            File relatorio = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            if (!relatorio.exists()) {
                relatorio.createNewFile();
            }
            BufferedWriter gravar = new BufferedWriter(new FileWriter(relatorio, true));
            gravar.newLine();
            gravar.write("<tr>");
            gravar.newLine();
            gravar.write("<td>");
            gravar.write("(" + this.xInicial + ", " + this.yInicial + ")");
            gravar.write("</td>");
            gravar.newLine();
            gravar.write("<td>");
            gravar.write("(" + this.xFinal + ", " + this.yFinal + ")");
            gravar.write("</td>");
            gravar.newLine();
            gravar.write("<td class='time'>");
            gravar.write(String.valueOf(this.tempo) + " ms");
            gravar.write("</td>");
            gravar.newLine();
            gravar.write("<td>");
            gravar.write(String.valueOf(this.nos_exp));
            gravar.write("</td>");
            gravar.newLine();
            gravar.write("<td>");
            gravar.write(String.valueOf(this.custo));
            gravar.write("</td>");
            gravar.newLine();
            gravar.write("<td>");
            gravar.write("<a href=\"desenho/" + this.desenho + ".txt\">Desenho</a>");
            gravar.write("</td>");
            gravar.newLine();
            gravar.write("</tr>");
            gravar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gera_fim() {
        try {
            File relatorio = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            if (!relatorio.exists()) {
                relatorio.createNewFile();
            }
            BufferedWriter gravar = new BufferedWriter(new FileWriter(relatorio, true));
            gravar.newLine();
            gravar.write("</html>");
            gravar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gera_footer_min() {
        try {
            File relatorio = new File(new File("build/classes/trabalho/relatorio/" + arquivo).getCanonicalPath());
            if (!relatorio.exists()) {
                relatorio.createNewFile();
            }
            BufferedWriter gravar = new BufferedWriter(new FileWriter(relatorio, true));
            gravar.newLine();
            gravar.write("</tbody>");
            gravar.newLine();
            gravar.write("</table>");
            gravar.newLine();
            gravar.newLine();
            gravar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
