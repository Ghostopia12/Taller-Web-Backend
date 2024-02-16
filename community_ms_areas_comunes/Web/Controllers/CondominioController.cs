﻿using Application.UseCase.Command.Condominios.CrearCondominio;
using Application.UseCase.Query.Condominios;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace Web.Controllers
{
    [Route("api/condominio")]
    [ApiController]
    public class CondominioController : ControllerBase
    {
        private readonly ILogger<CondominioController> _logger;
        private readonly IMediator _mediator;

        public CondominioController(IMediator mediator, ILogger<CondominioController> logger)
        {
            _mediator = mediator;
            _logger = logger;
        }

        [HttpPost]
        public async Task<IActionResult> CrearCondominio([FromBody] CrearCondominioCommand command)
        {
            try
            {
                var resultGuid = await _mediator.Send(command);
                return Ok(resultGuid);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error al crear el condominio");
                return BadRequest();
            }
        }

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var query = new GetListaCondominiosQuery();
            var result = await _mediator.Send(query);
            if (result == null)
            {
                return NotFound();
            }
            return Ok(result);
        }
    }
}
