import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBankInfos } from 'app/shared/model/bank-infos.model';
import { BankInfosService } from './bank-infos.service';

@Component({
  templateUrl: './bank-infos-delete-dialog.component.html',
})
export class BankInfosDeleteDialogComponent {
  bankInfos?: IBankInfos;

  constructor(protected bankInfosService: BankInfosService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bankInfosService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bankInfosListModification');
      this.activeModal.close();
    });
  }
}
