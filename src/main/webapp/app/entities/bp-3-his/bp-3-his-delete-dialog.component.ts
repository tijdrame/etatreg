import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBp3His } from 'app/shared/model/bp-3-his.model';
import { Bp3HisService } from './bp-3-his.service';

@Component({
  templateUrl: './bp-3-his-delete-dialog.component.html',
})
export class Bp3HisDeleteDialogComponent {
  bp3His?: IBp3His;

  constructor(protected bp3HisService: Bp3HisService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bp3HisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bp3HisListModification');
      this.activeModal.close();
    });
  }
}
