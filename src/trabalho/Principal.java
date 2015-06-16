/*
 * ATENÇÃO AS COORDENADAS SÃO DE ACORDO COM O PLANO CARTESIANO USUAL
 */
package trabalho;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Principal {

    public static int largura = 256;
    public static int altura = 256;

    //Função de transformação utilizada na tabela hash, com caracteristica de ser perfeita (não gera colisões), retorna um valor inteiro
    public static int fhash(int x, int y) {
        return x * 256 + y;
    }

    //Função heurística recebe valore de x1, y1 do meta, x2, y2 do filho sendo expandido
    public static double fheuristica(int x1, int y1, int x2, int y2) {
        return Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1)) + (0.5 * Math.min(Math.abs(x2 - x1), Math.abs(y2 - y1)));
    }

    //Função dijkstra, recebe valor w, sendo 0 = algoritmo de Dijkstra, 1 = Algoritmo A*, sendo maior que 1 = Algoritmo WA*, estado meta, mapa, nome do arquivo gerado p/ relatório, sendo que
    // se o mesmo tiver o valor "n" não é gerado relatorio
    public static void dijkstra(double w, Vertice ini, Vertice meta, char mapa[][], String arquivo) {
        PriorityQueue<Vertice> aberto = new PriorityQueue<>(); //Fila de prioriodade
        Map<Integer, Vertice> fechado = new Hashtable<>(); //Tabela Hash
        long inicio, fim, diferenca;
        boolean achou = false;
        inicio = System.currentTimeMillis(); //Função utilizada para medir o tempo de execução a partir daqui
        //Insere o estado inicial em aberto
        aberto.add(ini);
        // Enquanto aberto não estiver vazio repita
        while (aberto.size() != 0) {
            //Retira o elemento com menor custo em aberto
            Vertice c = aberto.poll();
            //Verifica o elemento removido é o meta se sim insere o meta em fechado e quebra repetiçao
            if (c.equals(meta)) {
                meta = c;
                fechado.put(fhash(c.getX(), c.getY()), c);
                achou = true;
                break;
            }
            //Verifica se é possiver percorre na posição do mapa (neste caso p/cima)
            //(*) Verifica se já está em aberto e se estiver verifica se o custo é menor, se sim realiza a troca em aberto
            //(**) Verifica se não está em fechado e aberto, antes de inserir em aberto
            if ((c.getY() - 1) >= 0) {
                if (mapa[c.getY() - 1][c.getX()] == '.') { // p/ cima
                    double custo = c.getCusto() + 1; //Custo até o pai
                    Vertice filho = new Vertice(c.getX(), c.getY() - 1,
                            custo + w * fheuristica(meta.getX(), meta.getY(), c.getX(), c.getY() - 1), custo, c);
                    if (/* (*) */aberto.contains(filho)) {
                        Iterator it = aberto.iterator();
                        Vertice v = null;
                        while (it.hasNext()) {
                            v = (Vertice) it.next();
                            if (filho.equals(v)) {
                                break;
                            }
                        }
                        if (v.getCusto() > filho.getCusto()) {
                            aberto.remove(v);
                            aberto.add(filho);
                        }
                    }
                    if (!fechado.containsKey(fhash(filho.getX(), filho.getY())) && !aberto.contains(filho)) /* (**) */ {
                        aberto.add(filho);
                    }
                }
            }
            // Verifica se é possivel andar p/ baixo e faz (*) e (**)
            if ((c.getY() + 1) < altura) {
                if (mapa[c.getY() + 1][c.getX()] == '.') {
                    double custo = c.getCusto() + 1; //Custo até o pai
                    Vertice filho = new Vertice(c.getX(), c.getY() + 1,
                            custo + w * fheuristica(meta.getX(), meta.getY(), c.getX(), c.getY() + 1), custo, c);
                    if (aberto.contains(filho)) {
                        Iterator it = aberto.iterator();
                        Vertice v = null;
                        while (it.hasNext()) {
                            v = (Vertice) it.next();
                            if (filho.equals(v)) {
                                break;
                            }
                        }
                        if (v.getCusto() > filho.getCusto()) {
                            aberto.remove(v);
                            aberto.add(filho);
                        }
                    }
                    if (!fechado.containsKey(fhash(filho.getX(), filho.getY())) && !aberto.contains(filho)) {
                        aberto.add(filho);
                    }
                }
            }
            //Verifica se é possivel andar p/ diagonal superior direita e faz (*) e (**)
            if ((c.getX() + 1) < largura && (c.getY() - 1) >= 0) {
                if (mapa[c.getY() - 1][c.getX() + 1] == '.'
                        && (mapa[c.getY() - 1][c.getX()] == '.' && mapa[c.getY()][c.getX() + 1] == '.')) {
                    double custo = c.getCusto() + 1.5; //Custo até o pai
                    Vertice filho = new Vertice(c.getX() + 1, c.getY() - 1,
                            custo + w * fheuristica(meta.getX(), meta.getY(), c.getX() + 1, c.getY() - 1), custo, c);
                    if (aberto.contains(filho)) {
                        Iterator it = aberto.iterator();
                        Vertice v = null;
                        while (it.hasNext()) {
                            v = (Vertice) it.next();
                            if (filho.equals(v)) {
                                break;
                            }
                        }
                        if (v.getCusto() > filho.getCusto()) {
                            aberto.remove(v);
                            aberto.add(filho);
                        }
                    }
                    if (!fechado.containsKey(fhash(filho.getX(), filho.getY())) && !aberto.contains(filho)) {
                        aberto.add(filho);
                    }
                }
            }
            //Verifica se é possivel andar p/ diagonal superior esquerda e faz (*) e (**)
            if ((c.getX() - 1) >= 0 && (c.getY() - 1) >= 0) {
                if (mapa[c.getY() - 1][c.getX() - 1] == '.'
                        && (mapa[c.getY() - 1][c.getX()] == '.' && mapa[c.getY()][c.getX() - 1] == '.')) {
                    double custo = c.getCusto() + 1.5; //Custo até o pai
                    Vertice filho = new Vertice(c.getX() - 1, c.getY() - 1,
                            custo + w * fheuristica(meta.getX(), meta.getY(), c.getX() - 1, c.getY() - 1), custo, c);
                    if (aberto.contains(filho)) {
                        Iterator it = aberto.iterator();
                        Vertice v = null;
                        while (it.hasNext()) {
                            v = (Vertice) it.next();
                            if (filho.equals(v)) {
                                break;
                            }
                        }
                        if (v.getCusto() > filho.getCusto()) {
                            aberto.remove(v);
                            aberto.add(filho);
                        }
                    }
                    if (!fechado.containsKey(fhash(filho.getX(), filho.getY())) && !aberto.contains(filho)) {
                        aberto.add(filho);
                    }

                }
            }
            //Verifica se é possivel andar p/ esquerda e faz (*) e (**)
            if ((c.getX() - 1) >= 0) {
                if (mapa[c.getY()][c.getX() - 1] == '.') {
                    double custo = c.getCusto() + 1; //Custo até o pai
                    Vertice filho = new Vertice(c.getX() - 1, c.getY(),
                            custo + w * fheuristica(meta.getX(), meta.getY(), c.getX() - 1, c.getY()), custo, c);
                    if (aberto.contains(filho)) {
                        Iterator it = aberto.iterator();
                        Vertice v = null;
                        while (it.hasNext()) {
                            v = (Vertice) it.next();
                            if (filho.equals(v)) {
                                break;
                            }
                        }
                        if (v.getCusto() > filho.getCusto()) {
                            aberto.remove(v);
                            aberto.add(filho);
                        }
                    }
                    if (!fechado.containsKey(fhash(filho.getX(), filho.getY())) && !aberto.contains(filho)) {
                        aberto.add(filho);
                    }
                }
            }
            //Verifica se é possivel andar p/ direita e faz (*) e (**)
            if ((c.getX() + 1) < largura) {
                if (mapa[c.getY()][c.getX() + 1] == '.') {
                    double custo = c.getCusto() + 1; //Custo até o pai
                    Vertice filho = new Vertice(c.getX() + 1, c.getY(),
                            custo + w * fheuristica(meta.getX(), meta.getY(), c.getX() + 1, c.getY()), custo, c);
                    if (aberto.contains(filho)) {
                        Iterator it = aberto.iterator();
                        Vertice v = null;
                        while (it.hasNext()) {
                            v = (Vertice) it.next();
                            if (filho.equals(v)) {
                                break;
                            }
                        }
                        if (v.getCusto() > filho.getCusto()) {
                            aberto.remove(v);
                            aberto.add(filho);
                        }
                    }
                    if (!fechado.containsKey(fhash(filho.getX(), filho.getY())) && !aberto.contains(filho)) {
                        aberto.add(filho);
                    }
                }
            }
            //Verifica se é possivel andar p/ diagonal inferior direita e faz (*) e (**)
            if ((c.getX() + 1) < largura && (c.getY() + 1) < altura) {
                if (mapa[c.getY() + 1][c.getX() + 1] == '.'
                        && (mapa[c.getY()][c.getX() + 1] == '.' && mapa[c.getY() + 1][c.getX()] == '.')) {
                    double custo = c.getCusto() + 1.5; //Custo até o pai
                    Vertice filho = new Vertice(c.getX() + 1, c.getY() + 1,
                            custo + w * fheuristica(meta.getX(), meta.getY(), c.getX() + 1, c.getY() + 1), custo, c);
                    if (aberto.contains(filho)) {
                        Iterator it = aberto.iterator();
                        Vertice v = null;
                        while (it.hasNext()) {
                            v = (Vertice) it.next();
                            if (filho.equals(v)) {
                                break;
                            }
                        }
                        if (v.getCusto() > filho.getCusto()) {
                            aberto.remove(v);
                            aberto.add(filho);
                        }
                    }
                    if (!fechado.containsKey(fhash(filho.getX(), filho.getY())) && !aberto.contains(filho)) {
                        aberto.add(filho);
                    }

                }
            }
            //Verifica se é possivel andar p/ diagonal inferior esquerda e faz (*) e (**)
            if ((c.getX() - 1) >= 0 && (c.getY() + 1) < altura) {
                if (mapa[c.getY() + 1][c.getX() - 1] == '.'
                        && (mapa[c.getY() + 1][c.getX()] == '.' && mapa[c.getY()][c.getX() - 1] == '.')) {
                    double custo = c.getCusto() + 1.5; //Custo até o pai
                    Vertice filho = new Vertice(c.getX() - 1, c.getY() + 1,
                            custo + w * fheuristica(meta.getX(), meta.getY(), c.getX() - 1, c.getY() + 1), custo, c);
                    if (aberto.contains(filho)) {
                        Iterator it = aberto.iterator();
                        Vertice v = null;
                        while (it.hasNext()) {
                            v = (Vertice) it.next();
                            if (filho.equals(v)) {
                                break;
                            }
                        }
                        if (v.getCusto() > filho.getCusto()) {
                            aberto.remove(v);
                            aberto.add(filho);
                        }
                    }
                    if (!fechado.containsKey(fhash(filho.getX(), filho.getY())) && !aberto.contains(filho)) {
                        aberto.add(filho);
                    }
                }
            }
            fechado.put(fhash(c.getX(), c.getY()), c);
        }
        //Fim
        fim = System.currentTimeMillis(); //Pega o tempo final da execução do trecho acima
        diferenca = fim - inicio; // Calcula o tempo em millisegundos
        //Mostra quantos Vertice estão em fechado
        System.out.println("Número de nós expandidos: " + fechado.size());
        int n_nos = fechado.size();
        System.out.println("Tempo Gasto: " + diferenca + " ms");
        //Recebe o estado meta
        Vertice caminho = meta;

        char[][] desenho = null;
        //Verifica se a opção solicitada gera relatório (#)
        if (arquivo != "n") {
            desenho = geraMapa(Integer.parseInt(String.valueOf(arquivo.charAt(5))));
            //Percorre todos os estados a partir do meta, até encontrar um valor null
            while (caminho.getPai() != null) {
                desenho[caminho.getY()][caminho.getX()] = 'X';
                System.out.print("<" + caminho.getX() + ", " + caminho.getY() + ", " + caminho.getCusto() + "> ");
                caminho = caminho.getPai();
            }
            desenho[ini.getY()][ini.getX()] = 'I';
            System.out.print("<" + ini.getX() + ", " + ini.getY() + ", " + ini.getCusto() + "> ");
        } else {
            //Percorre todos os estados a partir do meta, até encontrar um valor null
            while (caminho.getPai() != null) {
                System.out.print("<" + caminho.getX() + ", " + caminho.getY() + ", " + caminho.getCusto() + "> ");
                caminho = caminho.getPai();
            }
            System.out.print("<" + ini.getX() + ", " + ini.getY() + ", " + ini.getCusto() + "> ");
        }
        //Grava no arquivo de relatório, ver (#)
        if (arquivo != "n") {
            //Recebe os dados para geração do relatório (xi, yi), (xf, yf), tempo, número de nós expandidos, custo, arquivo a ser gravado
            Relatorio novo = new Relatorio(ini.getX(), ini.getY(), meta.getX(), meta.getY(), diferenca, n_nos, (float) meta.getCusto(), arquivo, String.valueOf(fhash(ini.getX(), ini.getY())) + String.valueOf(fhash(meta.getX(), meta.getY())) + String.valueOf(inicio));
            //Gera corpo do relatório
            novo.gerar_body();
            //Cria um arquivo txt com o mapa percorrido
            try {
                File mapa_txt = new File(new File("build/classes/trabalho/relatorio/desenho/" + String.valueOf(fhash(ini.getX(), ini.getY())) + String.valueOf(fhash(meta.getX(), meta.getY())) + String.valueOf(inicio) + ".txt").getCanonicalPath());
                if (!mapa_txt.exists()) {
                    mapa_txt.createNewFile();
                }
                BufferedWriter gravar = new BufferedWriter(new FileWriter(mapa_txt));
                for (int i = 0; i < altura; i++) {
                    for (int j = 0; j < largura; j++) {
                        gravar.write(desenho[i][j]);
                    }
                    gravar.newLine();
                }
                gravar.close();
            } catch (IOException e) {
                System.err.print("Impossive gravar ou criar txt!");
            }
        }
    }

    //Gera 8 relatórios, um de cada mapa com dados randômicos, sendo o valor n recebido o número de dados em cada relatório 
    public static void gera_relatorio_todos(int n, double w) {
        Random gerador = new Random();
        for (int i = 1; i <= 8; i++) {
            Vertice ini = new Vertice();
            Vertice meta = new Vertice();
            //Realiza a leitura e criação do mapa (matriz)
            char mapa[][] = geraMapa(i);
            //Fim da criação do mapa (matriz)
            //Cria nome do relatório
            String arquivo = "index" + i + ".html";
            //Cria relatório
            Relatorio v = new Relatorio(arquivo);
            //Limpa arquivo se ele existir
            v.apaga_arquivo();
            // Gera cabeçalho do relatório
            v.gera_head();
            //Inicio da geração do relatório
            ArrayList<Vertice> lista_ini = new ArrayList<>();
            ArrayList<Vertice> lista_meta = new ArrayList<>();

            for (int conta_laco = 1; conta_laco <= n; conta_laco++) {
                int xi, yi, xf, yf;
                //Gera valores de (x, y), para o estado inicial
                do {
                    xi = gerador.nextInt(256);
                    yi = gerador.nextInt(256);
                } while (mapa[yi][xi] != '.');
                //Gera valores de (x, y), para o estado meta
                do {
                    xf = gerador.nextInt(256);
                    yf = gerador.nextInt(256);
                } while (mapa[yf][xf] != '.');
                //Cria uma lista de valor de valores aleatórios de ini e meta 
                lista_ini.add(new Vertice(xi, yi, 0));
                lista_meta.add(new Vertice(xf, yf, 0));
            }

            v.gera_titulo("Algoritmo de Dijkstra");
            v.gera_prox("");
            for (int li = 0; li < lista_ini.size(); li++) {
                dijkstra(0, lista_ini.get(li), lista_meta.get(li), mapa, arquivo);
            }
            v.gera_footer_min();//Gera rodapé do relatório
            v.gera_texto("Tempo total: " + "<span id='dij'></span> ms");
            v.gera_texto("<br/>Média de Tempo: " + "<span id='dij_m'></span> ms");
            v.gera_texto("<br/>Total de nós expandidos: " + "<span id='dij_no'></span>");
            v.gera_texto("<br/>Média de nós expandidos: " + "<span id='dij_me'></span>");
            v.gera_texto("<br/>Total Custo: " + "<span id='dij_custo'></span>");
            v.gera_texto("<br/>Média de Custos: " + "<span id='dij_customedia'></span>");
            v.gera_titulo("Algoritmo A*");
            v.gera_prox("aestrela");
            for (int li = 0; li < lista_ini.size(); li++) {
                dijkstra(1, lista_ini.get(li), lista_meta.get(li), mapa, arquivo);
            }
            v.gera_footer_min();//Gera rodapé do relatório
            v.gera_texto("Tempo total: " + "<span id='a'></span> ms");
            v.gera_texto("<br/>Média de Tempo: " + "<span id='a_m'></span> ms");
            v.gera_texto("<br/>Total de nós expandidos: " + "<span id='a_no'></span>");
            v.gera_texto("<br/>Média de nós expandidos: " + "<span id='a_me'></span>");
            v.gera_texto("<br/>Total Custo: " + "<span id='a_custo'></span>");
            v.gera_texto("<br/>Média de Custos: " + "<span id='a_customedia'></span>");
            v.gera_titulo("Algoritmo WA* (W = " + w + ")");
            v.gera_prox("waestrela");
            for (int li = 0; li < lista_ini.size(); li++) {
                dijkstra(w, lista_ini.get(li), lista_meta.get(li), mapa, arquivo);
            }
            v.gera_footer_min();//Gera rodapé do relatório
            v.gera_texto("Tempo total: " + "<span id='wa'></span> ms");
            v.gera_texto("<br/>Média de Tempo: " + "<span id='wa_m'></span> ms");
            v.gera_texto("<br/>Total de nós expandidos: " + "<span id='wa_no'></span>");
            v.gera_texto("<br/>Média de nós expandidos: " + "<span id='wa_me'></span>");
            v.gera_texto("<br/>Total Custo: " + "<span id='wa_custo'></span>");
            v.gera_texto("<br/>Média de Custos: " + "<span id='wa_customedia'></span>");
            v.gera_fim();
            //Fim da geração do relatório
        }
    }

    //Realiza a leitura e criação do mapa (matriz)
    public static char[][] geraMapa(int op) {
        char mapa[][] = new char[256][256];
        //Lança uma exceção se o mapa não existe
        try {
            File dados = new File(new File("build/classes/trabalho/map" + op + ".map").getCanonicalPath());
            FileReader fr = new FileReader(dados);
            BufferedReader leitor = new BufferedReader(fr);
            int h = 0;
            //Pula linha irrelevante :)
            String linha = leitor.readLine();
            linha = leitor.readLine();
            linha = leitor.readLine();
            linha = leitor.readLine();
            //Ler dados e preenche a matriz
            while (linha != null && h < 256) {
                linha = leitor.readLine();
                for (int j = 0; j < linha.length(); j++) {
                    mapa[h][j] = linha.charAt(j);
                }
                h++;
            }
            leitor.close();
        } catch (IOException e) {
            System.err.print("Arquivo de mapa não existe!");
        }
        return mapa;
    }

    public static void limpar_desenhos() {
        System.out.println("Aguarde apagando desenhos antigos :)");
        try {
            File folder = new File(new File("build/classes/trabalho/relatorio/desenho").getCanonicalPath());
            if (folder.isDirectory()) {
                File[] sun = folder.listFiles();
                for (File toDelete : sun) {
                    toDelete.delete();
                }
            }
        } catch (IOException e) {
            System.out.println("Diretorio 'desenho' não foi encontrado!");
        }
        System.out.println("Pronto :)");
    }

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int xi, yi, xf, yf;
        sair: // Label para realizar a quebra do laço
        while (true) {
            System.out.println("\n\nMENU - ALGORITMO DE DIJKSTRA");
            System.out.println("1 - Obter <x , y, custo>, passo a passo do percurso do estado inicial ao meta e o tempo, de um mapa especifico.");
            System.out.println("2 - Obter <x , y, custo>, passo a passo do percurso do estado inicial(aleatório) ao meta(aleatório) "
                    + "e o tempo, de todos os mapas gerando relatório.");
            System.out.println("3 - Sair");
            System.out.println("\nDigite a opção desejada:");
            String op = sn.next();
            switch (op) {
                case "1":
                    System.out.print("Digite os dados do estado inicial(x, y) como plano cartesiano, primeiro X(ENTER), depois Y(ENTER): ");
                    xi = sn.nextInt();
                    yi = sn.nextInt();
                    System.out.print("Digite os dados do estado inicial(x, y) como plano cartesiano, primeiro X(ENTER), depois Y(ENTER): : ");
                    xf = sn.nextInt();
                    yf = sn.nextInt();
                    System.out.print("Digite o mapa que você deseja obter os dados (1 ao 8): ");
                    int map = sn.nextInt();
                    System.out.print("Digite '0' p/ o Algoritmo de Dijkstra, '1' p/ o algoritmo A* e outros valores p/ o WA*(Separação de casas decimais deve ser por vírgula): ");
                    double tipo = sn.nextDouble();
                    //Chama a função dijkstra, passando um objeto Vertice(ver classe Vertice para mais detalhes)
                    dijkstra(tipo, new Vertice(xi, yi, 0), new Vertice(xf, yf, 0), geraMapa(map), "n");
                    break;
                case "2":
                    limpar_desenhos();
                    System.out.print("Digite o número de dados por relatório que deseja visualizar: ");
                    int n = sn.nextInt();
                    System.out.print("Digite o valor de W para ser utilizado no alogrimo WA*(Separação de casas decimais deve ser por vírgula): ");
                    double tip = sn.nextDouble();
                    gera_relatorio_todos(n, tip);
                    System.out.println("\n\nOs dados dos relatórios encontram-se linkados em \"/build/classes/trabalho/relatorio/index.html\", do diretório onde econtram-se os arquivos do projeto.");
                    break;
                case "3":
                    break sair; //Quebra laço
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
