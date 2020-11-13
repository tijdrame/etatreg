import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBpbqe } from 'app/shared/model/bpbqe.model';
import { BpbqeService } from './bpbqe.service';

@Component({
  templateUrl: './bpbqe-delete-dialog.component.html',
})
export class BpbqeDeleteDialogComponent {
  bpbqe?: IBpbqe;

  constructor(protected bpbqeService: BpbqeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bpbqeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bpbqeListModification');
      this.activeModal.close();
    });
  }
}
