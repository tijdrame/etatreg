import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';
import { AcheteurVendeurService } from './acheteur-vendeur.service';

@Component({
  templateUrl: './acheteur-vendeur-delete-dialog.component.html',
})
export class AcheteurVendeurDeleteDialogComponent {
  acheteurVendeur?: IAcheteurVendeur;

  constructor(
    protected acheteurVendeurService: AcheteurVendeurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.acheteurVendeurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('acheteurVendeurListModification');
      this.activeModal.close();
    });
  }
}
