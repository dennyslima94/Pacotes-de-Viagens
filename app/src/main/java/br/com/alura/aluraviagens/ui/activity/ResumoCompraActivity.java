package br.com.alura.aluraviagens.ui.activity;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

public class ResumoCompraActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo da compra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        setTitle(TITULO_APPBAR);
        carregaPacoteRecebido();


    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)){
            Pacote pacote = (Pacote)intent.getSerializableExtra(CHAVE_PACOTE);
            inicializaCampos(pacote);
        }
    }

    private void inicializaCampos(Pacote pacote) {
        mostraLocal(pacote);
        mostraPreco(pacote);
        mostraData(pacote);
        mostraImagem(pacote);
    }

    private void mostraLocal(Pacote pacoteSaoPaulo) {
        TextView local = findViewById(R.id.resumo_compra_local);
        local.setText(pacoteSaoPaulo.getLocal());
    }

    private void mostraData(Pacote pacote) {
            TextView data = findViewById(R.id.resumo_compra_data);
            String dataFormataDaViagem
                    = DataUtil.periodoEmTexto(pacote.getDias());
            data.setText(dataFormataDaViagem);
        }


        private void mostraPreco(Pacote pacote) {
            TextView preco = findViewById(R.id.resumo_compra_preco);
            String moedaBrasileira = MoedaUtil.formataParaBrasileiro(
                    pacote.getPreco());
            preco.setText(moedaBrasileira);
        }

        private void mostraImagem(Pacote pacote) {
            ImageView imagem = findViewById(R.id.resumo_compra_imagem);
            Drawable drawableDoPacote = ResourceUtil.
                    devolveDrawable(this, pacote.getImagem());
            imagem.setImageDrawable(drawableDoPacote);
        }
}