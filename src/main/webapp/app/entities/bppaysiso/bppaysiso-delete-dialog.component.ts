import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBppaysiso } from 'app/shared/model/bppaysiso.model';
import { BppaysisoService } from './bppaysiso.service';

@Component({
  templateUrl: './bppaysiso-delete-dialog.component.html',
})
export class BppaysisoDeleteDialogComponent {
  bppaysiso?: IBppaysiso;

  constructor(protected bppaysisoService: BppaysisoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bppaysisoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bppaysisoListModification');
      this.activeModal.close();
    });
  }
}
