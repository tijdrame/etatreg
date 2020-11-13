import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBpnaema } from 'app/shared/model/bpnaema.model';
import { BpnaemaService } from './bpnaema.service';

@Component({
  templateUrl: './bpnaema-delete-dialog.component.html',
})
export class BpnaemaDeleteDialogComponent {
  bpnaema?: IBpnaema;

  constructor(protected bpnaemaService: BpnaemaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bpnaemaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bpnaemaListModification');
      this.activeModal.close();
    });
  }
}
