import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChargeFile } from 'app/shared/model/charge-file.model';
import { ChargeFileService } from './charge-file.service';

@Component({
  templateUrl: './charge-file-delete-dialog.component.html',
})
export class ChargeFileDeleteDialogComponent {
  chargeFile?: IChargeFile;

  constructor(
    protected chargeFileService: ChargeFileService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.chargeFileService.delete(id).subscribe(() => {
      this.eventManager.broadcast('chargeFileListModification');
      this.activeModal.close();
    });
  }
}
