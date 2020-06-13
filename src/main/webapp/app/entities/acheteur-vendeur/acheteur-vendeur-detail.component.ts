import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';

@Component({
  selector: 'jhi-acheteur-vendeur-detail',
  templateUrl: './acheteur-vendeur-detail.component.html',
})
export class AcheteurVendeurDetailComponent implements OnInit {
  acheteurVendeur: IAcheteurVendeur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ acheteurVendeur }) => (this.acheteurVendeur = acheteurVendeur));
  }

  previousState(): void {
    window.history.back();
  }
}
