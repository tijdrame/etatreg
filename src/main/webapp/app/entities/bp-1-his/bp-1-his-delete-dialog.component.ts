import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBp1His } from 'app/shared/model/bp-1-his.model';
import { Bp1HisService } from './bp-1-his.service';

@Component({
  templateUrl: './bp-1-his-delete-dialog.component.html',
})
export class Bp1HisDeleteDialogComponent {
  bp1His?: IBp1His;

  constructor(protected bp1HisService: Bp1HisService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bp1HisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bp1HisListModification');
      this.activeModal.close();
    });
  }
}
