import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBpitrs } from 'app/shared/model/bpitrs.model';
import { BpitrsService } from './bpitrs.service';

@Component({
  templateUrl: './bpitrs-delete-dialog.component.html',
})
export class BpitrsDeleteDialogComponent {
  bpitrs?: IBpitrs;

  constructor(protected bpitrsService: BpitrsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bpitrsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bpitrsListModification');
      this.activeModal.close();
    });
  }
}
