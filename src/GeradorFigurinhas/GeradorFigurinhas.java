package GeradorFigurinhas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeradorFigurinhas {

    public void cria(InputStream input, String nomeArquivo, String texto, InputStream overlay) throws IOException {
        // ler a imagem
        // File file = new File("entrada\\filme.jpg");
        //InputStream inputStream = new FileInputStream(file);
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        nomeArquivo = nomeArquivo.replace(":", " ");
        BufferedImage imagemOriginal = ImageIO.read(input);
        BufferedImage imagemSobreposicao = ImageIO.read(overlay);


        // cria nova imagem em memória com transparência e tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int acrescimo = altura / 5;
        imagemSobreposicao = resizeImage(imagemSobreposicao, acrescimo);
        int novaAltura = altura + acrescimo;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // desenhar a imagem de sobreposição no local adicionado
        graphics.drawImage(imagemSobreposicao, 0, altura, null);

        // configurar a fonte
        Font fonte = new Font("Impact", Font.BOLD, acrescimo / 2);
        graphics.setFont(fonte);
        graphics.setColor(Color.yellow);
        FontMetrics metrics = graphics.getFontMetrics();
        int larguraTexto = metrics.stringWidth(texto);
        int posicaoTextoX = (largura - larguraTexto) /2;
        int posicaoTextoY = altura + (acrescimo / 2);


        // escrever uma frase na nova imagem com outline
        graphics.drawString(texto, posicaoTextoX, posicaoTextoY);
        FontRenderContext context = graphics.getFontRenderContext();
        TextLayout layout = new TextLayout(texto, fonte, context);
        Shape outline = layout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);
        Stroke stroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(stroke);
        graphics.setColor(Color.black);
        graphics.draw(outline);
        graphics.setClip(outline);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

    private BufferedImage resizeImage(BufferedImage imagem, int altura){
        double ratio = (double) imagem.getHeight() / imagem.getWidth();
        int novaLargura = (int) (altura / ratio);
        BufferedImage resized = new BufferedImage(novaLargura, altura, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = resized.createGraphics();
        graphics.drawImage(imagem, 0,0, novaLargura ,altura, null);
        graphics.dispose();
        return resized;
    }
}
