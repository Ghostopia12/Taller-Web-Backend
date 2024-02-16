using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Infrastructure.EntityFramework.Migrations
{
    /// <inheritdoc />
    public partial class InitialStructure : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Condominio",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false),
                    nombre = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Condominio", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "Residente",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false),
                    deudor = table.Column<bool>(type: "boolean", nullable: false),
                    nombre = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Residente", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "Turno",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false),
                    inicio = table.Column<TimeOnly>(type: "time without time zone", nullable: false),
                    fin = table.Column<TimeOnly>(type: "time without time zone", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Turno", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "AreaComun",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false),
                    nombre = table.Column<string>(type: "text", nullable: false),
                    descripcion = table.Column<string>(type: "text", nullable: false),
                    capacidad_maxima = table.Column<int>(type: "integer", nullable: false),
                    estado = table.Column<string>(type: "text", nullable: false),
                    turno_id = table.Column<Guid>(type: "uuid", nullable: false),
                    condominio_id = table.Column<Guid>(type: "uuid", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_AreaComun", x => x.id);
                    table.ForeignKey(
                        name: "FK_AreaComun_Condominio_condominio_id",
                        column: x => x.condominio_id,
                        principalTable: "Condominio",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_AreaComun_Turno_turno_id",
                        column: x => x.turno_id,
                        principalTable: "Turno",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_AreaComun_condominio_id",
                table: "AreaComun",
                column: "condominio_id");

            migrationBuilder.CreateIndex(
                name: "IX_AreaComun_turno_id",
                table: "AreaComun",
                column: "turno_id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "AreaComun");

            migrationBuilder.DropTable(
                name: "Residente");

            migrationBuilder.DropTable(
                name: "Condominio");

            migrationBuilder.DropTable(
                name: "Turno");
        }
    }
}
