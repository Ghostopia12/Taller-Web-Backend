# Generated by Django 4.1.1 on 2024-02-14 02:53

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('webapi', '0002_categoria_plato_rest_plat_restaurante'),
    ]

    operations = [
        migrations.CreateModel(
            name='TipoCondominio',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre_tipo_condominio', models.CharField(max_length=50)),
            ],
        ),
        migrations.CreateModel(
            name='TipoDivision',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre_division', models.CharField(max_length=50)),
            ],
        ),
        migrations.CreateModel(
            name='Condominio',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=50)),
                ('tipoCondominioId', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='tipos', to='webapi.tipocondominio')),
                ('tipoDivisionId', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='divisiones', to='webapi.tipodivision')),
            ],
        ),
    ]
