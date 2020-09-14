import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICrpAtr } from 'app/shared/model/crp-atr.model';
import { CrpAtrService } from './crp-atr.service';

@Component({
  templateUrl: './crp-atr-delete-dialog.component.html',
})
export class CrpAtrDeleteDialogComponent {
  crpAtr?: ICrpAtr;

  constructor(protected crpAtrService: CrpAtrService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.crpAtrService.delete(id).subscribe(() => {
      this.eventManager.broadcast('crpAtrListModification');
      this.activeModal.close();
    });
  }
}
