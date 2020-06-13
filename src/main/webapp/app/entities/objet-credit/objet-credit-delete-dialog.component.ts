import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IObjetCredit } from 'app/shared/model/objet-credit.model';
import { ObjetCreditService } from './objet-credit.service';

@Component({
  templateUrl: './objet-credit-delete-dialog.component.html',
})
export class ObjetCreditDeleteDialogComponent {
  objetCredit?: IObjetCredit;

  constructor(
    protected objetCreditService: ObjetCreditService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.objetCreditService.delete(id).subscribe(() => {
      this.eventManager.broadcast('objetCreditListModification');
      this.activeModal.close();
    });
  }
}
