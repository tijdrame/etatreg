import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChargement } from 'app/shared/model/chargement.model';
import { ChargementService } from './chargement.service';

@Component({
  templateUrl: './chargement-delete-dialog.component.html',
})
export class ChargementDeleteDialogComponent {
  chargement?: IChargement;

  constructor(
    protected chargementService: ChargementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.chargementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('chargementListModification');
      this.activeModal.close();
    });
  }
}
