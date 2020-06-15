import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBduAutor } from 'app/shared/model/bdu-autor.model';

@Component({
  selector: 'jhi-bdu-autor-detail',
  templateUrl: './bdu-autor-detail.component.html',
})
export class BduAutorDetailComponent implements OnInit {
  bduAutor: IBduAutor | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bduAutor }) => (this.bduAutor = bduAutor));
  }

  previousState(): void {
    window.history.back();
  }
}
