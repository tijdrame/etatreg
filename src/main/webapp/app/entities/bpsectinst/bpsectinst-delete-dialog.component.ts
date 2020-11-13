import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBpsectinst } from 'app/shared/model/bpsectinst.model';
import { BpsectinstService } from './bpsectinst.service';

@Component({
  templateUrl: './bpsectinst-delete-dialog.component.html',
})
export class BpsectinstDeleteDialogComponent {
  bpsectinst?: IBpsectinst;

  constructor(
    protected bpsectinstService: BpsectinstService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bpsectinstService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bpsectinstListModification');
      this.activeModal.close();
    });
  }
}
