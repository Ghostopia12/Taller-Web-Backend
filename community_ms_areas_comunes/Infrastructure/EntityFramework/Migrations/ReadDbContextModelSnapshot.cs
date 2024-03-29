﻿// <auto-generated />
using System;
using Infrastructure.EntityFramework.Context;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace Infrastructure.EntityFramework.Migrations
{
    [DbContext(typeof(ReadDbContext))]
    partial class ReadDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "7.0.5")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            NpgsqlModelBuilderExtensions.UseIdentityByDefaultColumns(modelBuilder);

            modelBuilder.Entity("Infrastructure.EntityFramework.ReadModel.AreasComunes.AreaComunReadModel", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid")
                        .HasColumnName("id");

                    b.Property<int>("CapacidadMaxima")
                        .HasColumnType("integer")
                        .HasColumnName("capacidad_maxima");

                    b.Property<Guid>("CondominioId")
                        .HasColumnType("uuid")
                        .HasColumnName("condominio_id");

                    b.Property<string>("Descripcion")
                        .IsRequired()
                        .HasColumnType("text")
                        .HasColumnName("descripcion");

                    b.Property<bool>("Eliminado")
                        .HasColumnType("boolean")
                        .HasColumnName("eliminado");

                    b.Property<string>("Estado")
                        .IsRequired()
                        .HasColumnType("text")
                        .HasColumnName("estado");

                    b.Property<DateTime>("FinCierre")
                        .HasColumnType("timestamp without time zone")
                        .HasColumnName("fin_cierre");

                    b.Property<string>("Nombre")
                        .IsRequired()
                        .HasColumnType("text")
                        .HasColumnName("nombre");

                    b.Property<Guid>("TurnoId")
                        .HasColumnType("uuid")
                        .HasColumnName("turno_id");

                    b.HasKey("Id");

                    b.HasIndex("CondominioId");

                    b.HasIndex("TurnoId");

                    b.ToTable("AreaComun", (string)null);
                });

            modelBuilder.Entity("Infrastructure.EntityFramework.ReadModel.Condominios.CondominioReadModel", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid")
                        .HasColumnName("id");

                    b.Property<bool>("Eliminado")
                        .HasColumnType("boolean")
                        .HasColumnName("eliminado");

                    b.Property<string>("Nombre")
                        .IsRequired()
                        .HasColumnType("text")
                        .HasColumnName("nombre");

                    b.HasKey("Id");

                    b.ToTable("Condominio", (string)null);
                });

            modelBuilder.Entity("Infrastructure.EntityFramework.ReadModel.Reservas.ReservaReadModel", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid")
                        .HasColumnName("id");

                    b.Property<Guid>("AreaComunId")
                        .HasColumnType("uuid")
                        .HasColumnName("area_comun_id");

                    b.Property<bool>("Eliminado")
                        .HasColumnType("boolean")
                        .HasColumnName("eliminado");

                    b.Property<string>("Estado")
                        .IsRequired()
                        .HasColumnType("text")
                        .HasColumnName("estado");

                    b.Property<DateTime>("Fin")
                        .HasColumnType("timestamp without time zone")
                        .HasColumnName("fin");

                    b.Property<DateTime>("Inicio")
                        .HasColumnType("timestamp without time zone")
                        .HasColumnName("inicio");

                    b.Property<Guid>("ResidenteId")
                        .HasColumnType("uuid")
                        .HasColumnName("residente_id");

                    b.HasKey("Id");

                    b.HasIndex("AreaComunId");

                    b.HasIndex("ResidenteId");

                    b.ToTable("Reserva", (string)null);
                });

            modelBuilder.Entity("Infrastructure.EntityFramework.ReadModel.Residentes.ResidenteReadModel", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid")
                        .HasColumnName("id");

                    b.Property<bool>("Deudor")
                        .HasColumnType("boolean")
                        .HasColumnName("deudor");

                    b.Property<bool>("Eliminado")
                        .HasColumnType("boolean")
                        .HasColumnName("eliminado");

                    b.Property<string>("Nombre")
                        .IsRequired()
                        .HasColumnType("text")
                        .HasColumnName("nombre");

                    b.HasKey("Id");

                    b.ToTable("Residente", (string)null);
                });

            modelBuilder.Entity("Infrastructure.EntityFramework.ReadModel.Turnos.TurnoReadModel", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid")
                        .HasColumnName("id");

                    b.Property<bool>("Eliminado")
                        .HasColumnType("boolean")
                        .HasColumnName("eliminado");

                    b.Property<TimeOnly>("Fin")
                        .HasColumnType("time without time zone")
                        .HasColumnName("fin");

                    b.Property<TimeOnly>("Inicio")
                        .HasColumnType("time without time zone")
                        .HasColumnName("inicio");

                    b.HasKey("Id");

                    b.ToTable("Turno", (string)null);
                });

            modelBuilder.Entity("Infrastructure.EntityFramework.ReadModel.AreasComunes.AreaComunReadModel", b =>
                {
                    b.HasOne("Infrastructure.EntityFramework.ReadModel.Condominios.CondominioReadModel", "Condominio")
                        .WithMany()
                        .HasForeignKey("CondominioId")
                        .OnDelete(DeleteBehavior.Restrict)
                        .IsRequired();

                    b.HasOne("Infrastructure.EntityFramework.ReadModel.Turnos.TurnoReadModel", "Turno")
                        .WithMany()
                        .HasForeignKey("TurnoId")
                        .OnDelete(DeleteBehavior.Restrict)
                        .IsRequired();

                    b.Navigation("Condominio");

                    b.Navigation("Turno");
                });

            modelBuilder.Entity("Infrastructure.EntityFramework.ReadModel.Reservas.ReservaReadModel", b =>
                {
                    b.HasOne("Infrastructure.EntityFramework.ReadModel.AreasComunes.AreaComunReadModel", "AreaComun")
                        .WithMany()
                        .HasForeignKey("AreaComunId")
                        .OnDelete(DeleteBehavior.Restrict)
                        .IsRequired();

                    b.HasOne("Infrastructure.EntityFramework.ReadModel.Residentes.ResidenteReadModel", "Residente")
                        .WithMany()
                        .HasForeignKey("ResidenteId")
                        .OnDelete(DeleteBehavior.Restrict)
                        .IsRequired();

                    b.Navigation("AreaComun");

                    b.Navigation("Residente");
                });
#pragma warning restore 612, 618
        }
    }
}
